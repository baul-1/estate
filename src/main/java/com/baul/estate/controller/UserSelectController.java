package com.baul.estate.controller;

import com.baul.estate.config.CMap;
import com.baul.estate.config.CParam;
import com.baul.estate.config.CResult;
import com.baul.estate.config.GetParam;
import com.baul.estate.mapper.SelectMapper;
import com.baul.estate.security.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/s")
public class UserSelectController {

    @Autowired
    SelectMapper selectMapper;

    @RequestMapping(value = "/listProvince.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listProvince(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listProvince();

        ret.put("provincelist", list);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/listArea.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listArea(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "provincepkey");

        List<CMap> list = selectMapper.listArea(param);

        ret.put("arealist", list);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/listTown.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listTown(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "areapkey");

        List<CMap> list = selectMapper.listTown(param);

        ret.put("townlist", list);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/listEstate.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listEstate(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listEstate(param);

        ret.put("estatelist", list);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/listOwner.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listOwner(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listOwner(param);

        ret.put("ownerlist", list);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/listUser.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listUser(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listUser(param);

        ret.put("userlist", list);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/listPossession.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listPossession(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listPossession(param);

        ret.put("possessionlist", list);

        ret.setStatus("ok");

        return ret;
    }



    @RequestMapping(value = "/listPossessionByEstate.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listPossessionByEstate(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t","officialestatepkey");

        List<CMap> list = selectMapper.listPossessionByEstate(param);

        ret.put("possessionlist", list);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/listPossessionByOwner.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listPossessionByOwner(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "ownerpkey");

        List<CMap> list = selectMapper.listPossessionByOwner(param);

        ret.put("possessionlist", list);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/listMortgagee.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listMortgagee(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listMortgagee(param);

        ret.put("mortgageelist", list);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/listMortgage.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listMortgage(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> mortgageList = selectMapper.listMortgage(param);

        for (CMap mortgage : mortgageList) {
            CMap possession  = selectMapper.listMortgageByPossessionDetail(mortgage);
            mortgage.put("possession", possession);
        }

        ret.put("mortgagelist", mortgageList);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/listUserByUserGroup.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listUserByUserGroup(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t","usergroup");

        List<CMap> list = selectMapper.listUserByUserGroup(param);

        ret.put("userlist", list);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/listAgencyByUserGroup.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listAgencyByUserGroup(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "usergroup");

        List<CMap> list = selectMapper.listAgencyByUserGroup(param);

        ret.put("userlist", list);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/listContract.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listContract(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listContract(param);

        for (CMap contract : list) {
            List<CMap> pList = selectMapper.listContractByPossessionDetail(contract);
            contract.put("possession", pList.get(0));
        }

        ret.put("contractlist", list);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/listContractByAgency.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listContractByAgency (@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listContractByAgency(param);

        ret.put("contractlist", list);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/listTeam.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listTeamByHQ (@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> listHQ = selectMapper.listTeamByHq(param);

        for (CMap HQ : listHQ) {
            List<CMap> listT = selectMapper.listTeamByT(HQ);
            for (CMap team : listT) {
                 List<CMap> userList =  selectMapper.listUserByTeam(team);
                 team.put("userlist",userList);
            }
            HQ.put("teamlist", listT);
        }


        ret.put("hqlist", listHQ);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/listVote.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listVote (@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listVote(param);

        for (CMap vote : list) {
            CMap address = selectMapper.getOwnerEstateAddress(vote);

            vote.put("townname", "");
            vote.put("officialaddress", "");
            vote.put("officialkind", "");

            if(address != null){
                vote.put("townname", address.getS("townname"));
                vote.put("officialaddress", address.getS("officialaddress"));
                vote.put("officialkind", address.getS("officialkind"));
            }


        }

        ret.put("votelist", list);

        ret.setStatus("ok");

        return ret;
    }




    @RequestMapping(value = "/listWorkPlan.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult listWorkPlan (@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        List<CMap> list = selectMapper.listWorkPlan(param);

        for (CMap work : list) {
            CMap a18 = selectMapper.getOwnerByContractAndPossession(work);
            // p.possessionpkey, p.ownerpkey, t.townname, oe.officialaddress, oe.officialkind, oe.officialmeter
            work.put("townname",a18.getS("townname"));
            work.put("officialaddress",a18.getS("officialaddress"));
            work.put("officialkind",a18.getS("officialkind"));
            work.put("officialmeter",a18.getS("officialmeter"));
            work.put("possessionmeter",a18.getS("possessionmeter"));
            work.put("beforeprice",a18.getS("beforeprice"));
            work.put("afterprice",a18.getS("afterprice"));
            work.put("regprice",a18.getS("regprice"));


        }

        ret.put("contractlist", list);

        ret.setStatus("ok");

        return ret;
    }






    @RequestMapping(value = "/getEstate.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getEstate(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "officialestatepkey");

        CMap estate = selectMapper.getEstate(param);

        ret.put("estate", estate);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/getPossession.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getPossession(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "possessionpkey");

        CMap possession = selectMapper.getPossession(param);

        CMap estate = selectMapper.getPossessionByEstate(possession);

        CMap owner = selectMapper.getPossessionByOwner(possession);

        List<CMap> mortgageList =  selectMapper.listPossessionByMortgage(possession);

        possession.put("owner", owner);
        possession.put("estate", estate);
        possession.put("mortgagelist", mortgageList);

        ret.put("possession", possession);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/getOwner.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getOwner(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "ownerpkey");
        param.validateEmpty("t", "ownerpkey");

        CMap owner = selectMapper.getOwner(param);

        ret.put("owner", owner);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/getUser.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getUser(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "userpkey");

        CMap user = selectMapper.getUser(param);

        ret.put("user", user);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/getMortgage.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getMortgage(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "mortgagepkey");

        CMap mortgage = selectMapper.getMortgage(param);

        List<CMap> possessionList = selectMapper.listMortgageByPossession(param);

        mortgage.put("possessionlist", possessionList);

        ret.put("mortgage",mortgage);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/getMortgagee.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getMortgagee(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "mortgageepkey");

        CMap mortgagee = selectMapper.getMortgagee(param);

        ret.put("mortgagee", mortgagee);

        ret.setStatus("ok");

        return ret;
    }

    @RequestMapping(value = "/getContract.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getContract(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "contractpkey");

        CMap contract = selectMapper.getContract(param);

        List<CMap> possessionList = selectMapper.listContractByPossessionDetail(param);

        contract.put("possessionlist", possessionList);
        contract.put("ownergroup", possessionList.get(0).get("ownergroup"));

        ret.put("contract", contract);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/getContractByAgency.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getContractByAgency(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "contractpkey");

        CMap contract = selectMapper.getContractByAgency(param);

        List<CMap> mortgageList = selectMapper.listMortgageByContract(param);

        contract.put("mortgagelist", mortgageList);

        ret.put("contract", contract);

        ret.setStatus("ok");

        return ret;
    }


    @RequestMapping(value = "/getOwnerDetailAboutVote.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getOwnerDetailAboutVote(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t", "ownerpkey");

        CMap ownerDetail = new CMap();

        List<CMap> possessionList = selectMapper.listPossessionByOwner(param);
        List<CMap> voteList = selectMapper.listVoteByOwner(param);
        CMap user = selectMapper.getUserByOwner(param);

        if(user == null){
            user = new CMap();
            user.put("hqname", "");
            user.put("teamname","");
            user.put("username","");
            user.put("userid","");
            user.put("userphone","");
        }

        if (possessionList != null) ownerDetail.put("possessionlist", possessionList);
        if (voteList != null) ownerDetail.put("votelist", voteList);
        ownerDetail.put("user", user);




        ret.put("ownerdetail",ownerDetail);


        ret.setStatus("ok");

        return ret;
    }






    @RequestMapping(value = "/getAdminDashBoard.*",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    public CResult getAdminDashBoard(@GetParam CParam param){

        CResult ret = new CResult();

        User userInfo = param.getUser();

        param.validateEmpty("t");

        CMap statusCnt = selectMapper.getContractStatusCntByAdmin(param);

        CMap priceSum = selectMapper.getContractPriceSumByAdmin(param);

        CMap ownerCnt = selectMapper.getOwnerAndContractCnt(param);

        CMap estateCnt = selectMapper.getEstateKindLandAndContractCnt(param);

        CMap estateMeterSum = selectMapper.getEstateKindLandMeterAndContractCnt(param);

        CMap possessionBeforePriceSum = selectMapper.getPossessionBeforePriceAndContract(param);

        CMap contractRegPriceSum = selectMapper.getContractRegPriceSum(param);

        CMap voteCnt = selectMapper.getVoteAndContractCnt(param);

        CMap ownerGroupCnt = selectMapper.getOwnerGroupCnt(param);

        //List<CMap> listTeamCnt = selectMapper.listTeamContactCnt(param);





        //ret.put("statuscnt", statusCnt);
        //ret.put("pricesum", priceSum);
        ret.put("ownercnt", ownerCnt);
        ret.put("estatecnt", estateCnt);
        ret.put("estatemetersum", estateMeterSum);
        ret.put("possessionbeforepricesum", possessionBeforePriceSum);
        ret.put("contractregpricesum", contractRegPriceSum);
        ret.put("votecnt", voteCnt);
        ret.put("ownergroupcnt", ownerGroupCnt);
        //ret.put("listteamcnt",listTeamCnt );



        //System.out.println(BCrypt.hashpw("pass1994", BCrypt.gensalt()));

        ret.setStatus("ok");

        return ret;
    }











}
