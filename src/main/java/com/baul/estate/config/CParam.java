package com.baul.estate.config;

import com.baul.estate.security.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Map;


public class CParam extends CMap {
    CMap head = new CMap();
    RedisTemplate<String, Object> redis;


    public CParam(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }

    public void validateEmpty(String... keys) throws CParamValidateException{
        for (String key: keys) {
            if(!isEmpty(key)){
                throw new CParamValidateException(key);
            }
        }

    }





    public void setByRequestParam(Map r){
        this.putAll(r);
        return;
    }


    public User getUser(){
        User ret = null;

        HashOperations<String,String,String> der = redis.opsForHash();

        String token = this.getS("t");
        String rkey = "SEUM:SESSION:" + token;
        String pkey = der.get(rkey,"cuserpkey");

        // redis.expire(rkey, Duration.ofDays(1));

        ret = new User(der.get(rkey,"username"));
        ret.setUserPkey(pkey);

        this.put("cuserpkey", ret.getUserPkey());

        return ret;
    }


    public boolean isEmpty(String key) {
        String aa = this.getS(key,"0-12judhioewd789iohfug5tbh4o3itghf");

        if( "0-12judhioewd789iohfug5tbh4o3itghf".equals(aa)) return false;
        if("".equals(aa.trim())) return false;

        return true;
    }




    public class CParamValidateException extends RuntimeException {
        String problem = "";
        public CParamValidateException(String key) {
            this.problem = key;
        }

        @Override
        public String getMessage() {
            return this.problem;
        }
    }
}
