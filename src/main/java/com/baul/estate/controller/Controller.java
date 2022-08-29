package com.baul.estate.controller;

import com.baul.estate.config.CMap;
import com.baul.estate.config.CParam;
import com.baul.estate.config.CResult;
import com.baul.estate.config.PostParam;
import com.baul.estate.mapper.SelectMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@RestController
public class Controller {

    @Value("${server.uploadroot}")
    String uploadroot ;

    @Autowired
    protected RedisTemplate<String,Object> redis;

    @Autowired
    SelectMapper selectMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @RequestMapping(value = "/uploadFile.*", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*")
    public Mono<CResult> uploadFile(@RequestPart("file")Mono<FilePart> monoFile){
        CResult ret = new CResult();
        String uuid = UUID.randomUUID().toString().replace("-","");
        final String[] UPLOADABLE_FILE_EXTENSION = { "png", "jpg", "clip","cmc", "psd", "zip", "tar", "sut", "cls" };

        return monoFile.map( fp -> {
            String fname = fp.filename();

            String ext;
            String uploaddir = uploadroot;
            String filename ="";

            try {

                ext = fname.substring(fname.lastIndexOf(".") + 1);
                String yyyymmdd = sdf.format(new Date());
                String yyyymm = yyyymmdd.substring(0,6);
                String dd = yyyymmdd.substring(6);


                filename = "/"+yyyymm+"/"+dd+"/";
                uploaddir = uploadroot+filename;
                Path path =  Paths.get(uploaddir);
                Files.createDirectories(path);

            }catch(Exception e){
                ret.setStatus("ERROR");
                return ret;
            }
            ext = ext.trim().toLowerCase();


            if(!Arrays.stream(UPLOADABLE_FILE_EXTENSION).anyMatch(ext::equals)){
                ret.setStatus("ERROR");
                return ret;
            }

            filename = filename+uuid+"."+ext;


            fp.transferTo(Paths.get(uploadroot+ filename)).subscribe();
            ret.put("filename", filename);
            return ret;

        });

    }

    @RequestMapping(value = "/login.*", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @CrossOrigin(origins = "*")
    public CResult login(@PostParam CParam param){

        CResult ret = new CResult();

        param.validateEmpty("userid","userpw");

        CMap userInfo  = selectMapper.getUserInfo(param);


        if(userInfo == null){
            ret.put("status","FAIL");
        }else {
            if(BCrypt.checkpw(param.getS("userpw"), userInfo.getS("userpw"))){
                userInfo.remove("userpw");

                try {
                    String token = makeSession(userInfo);
                    ret.put("status", "SUCCESS");
                    ret.put("rolepkey", userInfo.getS("rolepkey"));
                    ret.put("t", token);

                }catch(Exception e){
                    ret.put("status","FAIL2");
                }

            }else{
                ret.put("status","FAIL");
            }
        }

        ret.setStatus("OK");

        return ret;
    }

    @RequestMapping(value = "/logout.*", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @CrossOrigin(origins = "*")
    public CResult logout(@PostParam CParam param){

        CResult ret = new CResult();

        param.validateEmpty("t");

        redis.delete(getRedisKey(param.getS("t")));

        ret.setStatus("OK");

        return ret;
    }


    @RequestMapping(value = "/getUserInfo.*", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @CrossOrigin(origins = "*")
    public CResult getUserInfo(@PostParam CParam param){

        CResult ret = new CResult();

        param.validateEmpty("t");

        HashOperations<String, String, String> hash = redis.opsForHash();
        String name = hash.get(getRedisKey(param.getS("t")),"cusername");
        String role = hash.get(getRedisKey(param.getS("t")),"crolepkey");

        if(name == null){
            ret.put("islogin","N");
        }else{
            ret.put("username",name);
            ret.put("rolepkey",role);
            ret.put("islogin","Y");
        }

        ret.setStatus("OK");
        return ret;
    }





    private String getRedisKey(String token){
        String rkey = "SEUM:SESSION:" + token;
        return rkey;
    }

    protected String makeSession(CMap userinfo){
        String token = UUID.randomUUID().toString();
        String rkey = getRedisKey(token);

        try {
            HashOperations<String, String, String> hash = redis.opsForHash();

            Set sss = userinfo.keySet();

            for (Object ikey : sss) {
                String key = ikey.toString();
                hash.put(rkey, "c" + key, userinfo.getS(key));
            }

            redis.expire(rkey, Duration.ofDays(1));

            return token;

        }catch(Exception e){
            throw new RuntimeException("NOTLOGIN");
        }
    }



}
