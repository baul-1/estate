package com.baul.estate.mapper;

import com.baul.estate.config.CParam;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {


    @Insert("insert into officialestate (officialaddress, officialbuildingname,  officialnomination,  officialkind, officialmemo,  officialprice, officialmeter,  townpkey, projectpkey) select #{officialaddress}, #{officialbuildingname},  #{officialnomination}, #{officialkind}, #{officialmemo}, #{officialprice}, #{officialmeter},  #{townpkey}, 1 from user u where u.userstatus = 'NORMAL' and u.userpkey = 1 and u.rolepkey = 1 ")
    void addEstate(CParam param);

    @Insert("insert into owner (ownername, owneraddress, ownersector,  ownerphone, ownertel, owneridnumber, ownergroup, ownerage, first, addnumber, realrelation, pricerelation, detailrelation,   areapkey, projectpkey) select #{ownername}, #{owneraddress}, #{ownersector}, #{ownerphone}, #{ownertel}, #{owneridnumber}, #{ownergroup}, #{ownerage}, #{first}, #{addnumber}, #{realrelation}, #{pricerelation}, #{detailrelation}, #{areapkey} , 1 from user u where u.userstatus = 'NORMAL' and u.userpkey = 1 and u.rolepkey = 1")
    void addOwner(CParam param);

    @Insert("insert into possession ( possessionmeter, possessiontransferdate, possessioncause, beforeprice, afterprice, officialestatepkey, ownerpkey, projectpkey) " +
            "select #{possessionmeter}, #{possessiontransferdate}, #{possessioncause}, #{beforeprice}, #{afterprice}, #{officialestatepkey}, #{ownerpkey}, 1 from user u where u.userstatus = 'NORMAL' and u.userpkey = 1 and u.rolepkey = 1 ")
    void addPossession(CParam param);

    @Insert("insert into mortgagee (mortgageename, mortgageekind) select #{mortgageename}, #{mortgageekind} from user u where u.userstatus = 'NORMAL' and u.userpkey = 1 and u.rolepkey = 1")
    void addMortgagee(CParam param);

    @Insert("insert into mortgage (mortgagedate, mortgageprice, mortgagekind, mortgageepkey, mortgagecause, debtor, projectpkey) select #{mortgagedate}, #{mortgageprice}, #{mortgagekind}, #{mortgageepkey}, #{mortgagecause}, #{debtor}, 1 from user u where u.userstatus = 'NORMAL' and u.userpkey = 1 and u.rolepkey = 1")
    @Options(useGeneratedKeys = true, keyProperty = "mortgagepkey")
    int addMortgage(CParam param);

    @Insert("insert into mortgagepossession (mortgagepkey, possessionpkey) values (#{mortgagepkey}, #{possessionpkey})")
    void addMortgagePossession(CParam param);

    @Insert("insert into user (username, userid, userpw, userphone, usergroup, rolepkey) select #{username}, #{userid}, #{userpw}, #{userphone}, #{usergroup}, #{rolepkey} from user u where u.userstatus = 'NORMAL' and u.userpkey = -1 and u.rolepkey = -1")
    void addUser(CParam param);

    @Insert("insert into user (username, userid, userpw, userphone, usergroup, rolepkey, teampkey) select #{username}, #{userid}, #{userpw}, #{userphone}, #{usergroup}, #{rolepkey}, #{teampkey} from user u where u.userstatus = 'NORMAL' and u.userpkey = -1 and u.rolepkey = -1")
    void addUserForTeam(CParam param);

    @Insert("insert into contract ( regprice, startprice,  ingprice,  memo, endprice,  startdate, ingdate, enddate, contractstatus, userpkey, ownerpkey) select #{regprice}, #{startprice}, #{ingprice}, #{memo}, #{endprice}, #{startdate}, #{ingdate}, #{enddate}, #{contractstatus},  #{userpkey}, #{ownerpkey} from user u where u.userpkey = #{cuserpkey} and u.rolepkey = 1 and u.userstatus = 'NORMAL' ")
    @Options(useGeneratedKeys = true, keyProperty = "contractpkey")
    int addContract(CParam param);

    @Insert("insert contractpossession (contractpkey, possessionpkey) values  (#{contractpkey}, #{possessionpkey}) ")
    void addContractPossession(CParam param);


    @Insert("insert into vote (votekind, requestdate, landprice, landpricedate, activityprice, activitypricedate, ownerpkey) select #{votekind}, #{requestdate}, #{landprice}, #{landpricedate}, #{activityprice}, #{activitypricedate}, #{ownerpkey} from user u where u.userpkey = #{cuserpkey} and u.rolepkey = 1 and u.userstatus = 'NORMAL' ")
    void addVote(CParam param);







    // possessionmeter, possessiontransferdate, possessioncause, beforeprice, afterprice, officialestatepkey, ownerpkey, projectpkey
    @Update("update possession p, user u set  p.possessionmeter = #{possessionmeter}, p.possessiontransferdate = #{possessiontransferdate}, p.possessioncause = #{possessioncause}, p.beforeprice = #{beforeprice}, p.afterprice = #{afterprice},  officialestatepkey = #{officialestatepkey}, ownerpkey = #{ownerpkey} where p.possessionpkey = #{possessionpkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1")
    void modifyPossession(CParam param);

    @Update("update officialestate oe, user u set oe.officialaddress = #{officialaddress}, oe.officialbuildingname = #{officialbuildingname}, oe.officialnomination = #{officialnomination}, oe.officialprice =  #{officialprice}, oe.officialmeter = #{officialmeter}, oe.officialkind = #{officialkind}, oe.officialmemo = #{officialmemo}, oe.townpkey = #{townpkey} where oe.officialestatepkey = #{officialestatepkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1")
    void modifyEstate(CParam param);

    @Update("update owner o, user u set o.ownername = #{ownername}, o.owneraddress = #{owneraddress}, o.ownersector = #{ownersector}, o.ownerphone = #{ownerphone},  o.ownertel = #{ownertel}, o.owneridnumber = #{owneridnumber}, o.ownergroup = #{ownergroup}, o.ownerage = #{ownerage}, o.first = #{first}, o.addnumber = #{addnumber}, o.realrelation = #{realrelation}, o.pricerelation = #{pricerelation}, o.detailrelation = #{detailrelation}, o.areapkey = #{areapkey} where o.ownerpkey = #{ownerpkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1")
    void modifyOwner(CParam param);

    @Update("update user u, (select * from user where userpkey = #{cuserpkey}  and rolepkey = -1 and userstatus = 'NORMAL') a set u.username = #{username}, u.userphone = #{userphone}, u.userstatus = #{userstatus}, u.usergroup = #{usergroup}, u.rolepkey = #{rolepkey}, u.teampkey = null  where u.userpkey = #{userpkey} and a.userpkey = #{cuserpkey} ")
    void modifyUser(CParam param);

    @Update("update user u, (select * from user where userpkey = #{cuserpkey}  and rolepkey = -1 and userstatus = 'NORMAL') a set u.username = #{username}, u.userphone = #{userphone}, u.userstatus = #{userstatus}, u.usergroup = #{usergroup}, u.rolepkey = #{rolepkey}, u.teampkey = #{teampkey} where u.userpkey = #{userpkey} and a.userpkey = #{cuserpkey} ")
    void modifyUserTeam(CParam param);

    @Update("update user u, (select * from user where userpkey = #{cuserpkey} and rolepkey = -1 and userstatus = 'NORMAL') a  set u.userpw = #{userpw} where u.userpkey = #{userpkey} and a.userpkey = #{cuserpkey} ")
    void modifyUserPasswordByRoot(CParam param);


    @Update("update mortgagee mee, user u set mee.mortgageename = #{mortgageename} , mee.mortgageekind = #{mortgageekind} where mee.mortgageepkey = #{mortgageepkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1")
    void modifyMortgagee(CParam param);

    @Update("update mortgage me, user u set me.mortgagedate = #{mortgagedate}, me.mortgageprice = #{mortgageprice}, me.debtor = #{debtor}, me.mortgagecause = #{mortgagecause}, me.mortgagekind = #{mortgagekind}, me.mortgageepkey = #{mortgageepkey}  where me.mortgagepkey = #{mortgagepkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1")
    int modifyMortgage(CParam param);

    @Update("update contract c, user u set  c.regprice = #{regprice}, c.contractstatus = #{contractstatus}, c.startprice = #{startprice}, c.startdate = #{startdate}, c.ingprice = #{ingprice} , c.ingdate = #{ingdate}, c.endprice = #{endprice}, c.enddate = #{enddate}, c.memo = #{memo}, c.userpkey = #{userpkey}, c.ownerpkey = #{ownerpkey} where c.contractpkey = #{contractpkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1")
    int modifyContractByAdmin(CParam param);

    @Update("update vote v, user u set v.votekind = #{votekind}, v.requestdate = #{requestdate}, v.landprice = #{landprice}, v.landpricedate = #{landpricedate}, v.activityprice = #{activityprice}, v.activitypricedate = #{activitypricedate} where v.votepkey = #{votepkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1  ")
    void modifyVote(CParam param);




    @Delete("delete from mortgagepossession where mortgagepkey = #{mortgagepkey} and possessionpkey = #{possessionpkey}")
    void deleteMortgagePossession(CParam param);

    @Delete("delete from contractpossession cp where cp.contractpkey = #{contractpkey} and possessionpkey = #{possessionpkey}")
    void deleteContractPossession(CParam param);






}
