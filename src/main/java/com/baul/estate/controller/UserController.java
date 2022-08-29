package com.baul.estate.controller;

import com.baul.estate.config.CMap;
import com.baul.estate.config.CParam;
import com.baul.estate.config.CResult;
import com.baul.estate.config.PostParam;
import com.baul.estate.mapper.SelectMapper;
import com.baul.estate.mapper.UserMapper;
import com.baul.estate.security.User;

import com.baul.estate.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController extends  Controller {


    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;



    @RequestMapping(value = "/addEstate.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult addEstate(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t","officialaddress", "officialnomination", "officialkind", "officialprice", "officialmeter",  "townpkey");

        userMapper.addEstate(param);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/addOwner.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult addOwner(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t","ownername", "owneraddress", "areapkey");

        userMapper.addOwner(param);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/addPossession.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult addPossession(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t", "possessionmeter", "possessiontransferdate", "officialestatepkey",  "ownerpkey");

        userMapper.addPossession(param);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/addMortgagee.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult addMortgagee(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t", "mortgageename", "mortgageekind");

        userMapper.addMortgagee(param);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/addMortgage.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult addMortgage(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t","mortgagedate", "mortgageprice", "mortgagekind", "mortgageepkey",  "possessionlist");

        userService.addMortgage(param);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/addUser.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult addUser(@PostParam CParam param) {

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t","username", "userid", "userpw", "userphone", "usergroup", "rolepkey", "teampkey", "isteam");

        param.put("userpw", BCrypt.hashpw(param.getS("userpw"), BCrypt.gensalt()));

        if( "N".equals(param.getS("isteam"))){userMapper.addUser(param);}
        if( "Y".equals(param.getS("isteam"))){userMapper.addUserForTeam(param);}

        ret.setStatus("ok");

        return  ret;
    }

    @RequestMapping(value = "/addContract.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult addContract(@PostParam CParam param) {

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t", "regprice", "startprice", "ingprice", "endprice", "contractstatus", "possessionlist", "userpkey", "ownerpkey");

        userService.addContract(param);


        ret.setStatus("ok");

        return  ret;
    }

    @RequestMapping(value = "/addVote.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult addVote(@PostParam CParam param) {

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t", "votekind", "requestdate", "landprice", "landpricedate", "activityprice", "activitypricedate", "ownerpkey");

        userMapper.addVote(param);

        ret.setStatus("ok");

        return  ret;
    }










    @RequestMapping(value = "/modifyEstate.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult modifyEstate(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t","officialaddress", "officialnomination", "officialkind", "officialprice", "officialmeter", "officialestatepkey", "townpkey");

        userMapper.modifyEstate(param);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/modifyOwner.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult modifyOwner(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();
        // ownersector
        param.validateEmpty("t", "ownername", "owneraddress",  "areapkey",  "ownerpkey");

        userMapper.modifyOwner(param);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/modifyPossession.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult modifyPossession(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t", "possessionmeter", "possessiontransferdate", "possessionpkey", "ownerpkey", "officialestatepkey");

        userMapper.modifyPossession(param);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/modifyMortgagee.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult modifyMortgagee(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t", "mortgageename", "mortgageekind", "mortgageepkey");

        userMapper.modifyMortgagee(param);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/modifyMortgage.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult modifyMortgage(@PostParam CParam param){

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t","mortgagedate", "mortgageprice", "mortgagepkey", "mortgageepkey", "addpossessionlist", "deletepossessionlist", "isadd", "isdelete");

        userService.modifyMortgage(param);


        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/modifyUser.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult modifyUser(@PostParam CParam param) {

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t", "username", "userpkey", "userphone", "usergroup",  "userstatus", "rolepkey", "teampkey", "isteam");

        if("N".equals(param.getS("isteam"))) userMapper.modifyUser(param);
        if("Y".equals(param.getS("isteam"))) userMapper.modifyUserTeam(param);

        ret.setStatus("ok");

        return  ret;
    }

    @RequestMapping(value = "/modifyUserPasswordByRoot.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult modifyUserPasswordByRoot(@PostParam CParam param) {

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty( "t", "userpkey", "userpw");

        param.put("userpw", BCrypt.hashpw(param.getS("userpw"), BCrypt.gensalt()));

        userMapper.modifyUserPasswordByRoot(param);

        ret.setStatus("ok");

        return  ret;
    }


    @RequestMapping(value = "/modifyContractByAdmin.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult modifyContract(@PostParam CParam param) {

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t", "ownerpkey", "contractpkey", "regprice", "startprice", "ingprice", "endprice", "contractstatus",
                "addpossessionlist", "deletepossessionlist", "isadd", "isdelete", "userpkey");

        userService.modifyContract(param);

        ret.setStatus("ok");

        return  ret;
    }


    @RequestMapping(value = "/modifyVote.*", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult modifyVote(@PostParam CParam param) {

        CResult ret = new CResult();

        User user = param.getUser();

        param.validateEmpty("t", "votekind", "requestdate", "landprice", "landpricedate", "activityprice", "activitypricedate", "votepkey", "ownerpkey");


        userMapper.modifyVote(param);

        ret.setStatus("ok");

        return  ret;
    }



















}
