package com.baul.estate.mapper;

import com.baul.estate.config.CMap;
import com.baul.estate.config.CParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SelectMapper {



    @Select("select userpkey, username, userpw, rolepkey from user where userid = #{userid} and userstatus != 'BAN'")
    CMap getUserInfo(CParam param);


    @Select("select * from province")
    List<CMap> listProvince();

    @Select("select * from area where provincepkey = #{provincepkey}")
    List<CMap> listArea(CParam param);

    @Select("select * from town where areapkey = #{areapkey}")
    List<CMap> listTown(CParam param);


    @Select("select oe.*, t.townname from officialestate oe, town t, user u  where oe.townpkey = t.townpkey and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)")
    List<CMap> listEstate(CParam param);


    @Select("select o.* from owner o, user u where u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1) ")
    List<CMap> listOwner(CParam param);


    @Select("select a.* , te.teampkey hqpkey, te.teamname hqname, te.teamkind hqkind  from (select u.userpkey, u.username, u.usergroup, u.userid, u.usercreatedate, u.userstatus, u.userphone, r.*, te.teamname, te.teamkind, te.rel from user u join role r on u.rolepkey = r.rolepkey \n" +
            "left join team te on u.teampkey = te.teampkey , (select * from user where userpkey = #{cuserpkey} and userstatus = 'NORMAL' and rolepkey = -1 ) a \n" +
            "where a.userpkey = #{cuserpkey} order by u.usergroup asc) a left join team te on a.rel = te.teampkey where a.rolepkey != -1")
    List<CMap> listUser(CParam param);

    @Select("select u.userpkey, u.username, u.userid, u.userphone from user u where u.teampkey = #{teampkey} ")
    List<CMap> listUserByTeam(CMap param);


    @Select("select oe.officialaddress, oe.officialnomination, oe.officialkind, oe.officialmeter, t.townname, o.ownername, o.ownersector, p.* from possession p , user u, officialestate oe, owner o, town t where p.officialestatepkey = oe.officialestatepkey and oe.townpkey = t.townpkey and p.ownerpkey = o.ownerpkey and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1) order by oe.officialaddress asc")
    List<CMap> listPossession(CParam param);


    @Select("select o.ownername, p.*  from possession p , owner o, user u  where p.ownerpkey = o.ownerpkey and  p.officialestatepkey = #{officialestatepkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)")
    List<CMap> listPossessionByEstate(CParam param);


    @Select("select o.ownername, t.townname, oe.officialaddress, oe.officialmeter, oe.officialkind, p.* from possession p, owner o, officialestate oe, town t, user u  where p.ownerpkey = o.ownerpkey \n" +
            "and p.officialestatepkey = oe.officialestatepkey and oe.townpkey = t.townpkey and p.ownerpkey = #{ownerpkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)")
    List<CMap> listPossessionByOwner(CParam param);


    @Select("SELECT mee.*, sum(case when me.mortgageepkey <=> null then 0 else 1 end) mortgagecount  FROM user u , mortgagee mee left join mortgage me on mee.mortgageepkey = me.mortgageepkey where u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)  group by mee.mortgageepkey")
    List<CMap> listMortgagee(CParam param);

    @Select("select me.*, mee.* from mortgage me, mortgagee mee, user u  where me.mortgageepkey = mee.mortgageepkey and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)")
    List<CMap> listMortgage(CParam param);

    @Select("select t.townname, oe.officialaddress, oe.officialkind, oe.officialnomination, oe.officialmeter, p.possessionmeter, o.ownername from mortgagepossession mp, possession p, owner o , officialestate oe, town t where mp.mortgagepkey = #{mortgagepkey} and mp.possessionpkey = p.possessionpkey and p.ownerpkey = o.ownerpkey and p.officialestatepkey = oe.officialestatepkey and oe.townpkey = t.townpkey limit 1 ")
    CMap listMortgageByPossessionDetail(CMap param);

    @Select("select me.*, mee.* from mortgagepossession mp, mortgage me, mortgagee mee where mp.possessionpkey = #{possessionpkey} and mp.mortgagepkey = me.mortgagepkey and me.mortgageepkey = mee.mortgageepkey ")
    List<CMap> listPossessionByMortgage(CMap param);

    @Select("select p.possessionpkey, o.ownername, t.townname, oe.officialaddress, oe.officialkind, oe.officialmeter, p.possessionmeter, p.beforeprice, p.afterprice from mortgagepossession mp, possession p , officialestate oe, owner o, town t where mp.mortgagepkey = #{mortgagepkey} and mp.possessionpkey = p.possessionpkey and p.officialestatepkey = oe.officialestatepkey and p.ownerpkey = o.ownerpkey and oe.townpkey = t.townpkey")
    List<CMap> listMortgageByPossession(CParam param);




    @Select("select a.* from (select userpkey, username, userid, userphone from user where usergroup = #{usergroup} and userstatus = 'NORMAL' and userpkey != -1) a ,user u where u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1 ")
    List<CMap> listUserByUserGroup(CParam param);


    @Select("select a.* from (select userpkey, username, userid, userphone from user where usergroup = #{usergroup} and userstatus = 'NORMAL' and userpkey != -1 and rolepkey = 3) a ,user u where u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1 ")
    List<CMap> listAgencyByUserGroup(CParam param);



    @Select("select cr.*, u.username, u.userphone from contract cr, user u , (select rolepkey from user u where u.userpkey = #{cuserpkey} and u.rolepkey in (1) and u.userstatus = 'NORMAL' ) a where cr.userpkey = u.userpkey and a.rolepkey in (1) ;")
    List<CMap> listContract(CParam param);


    @Select("select * from contractpossession cp, possession p , owner o, officialestate oe, town t where cp.contractpkey = #{contractpkey} and cp.possessionpkey = p.possessionpkey  and p.ownerpkey = o.ownerpkey and p.officialestatepkey = oe.officialestatepkey and oe.townpkey = t.townpkey")
    List<CMap> listContractByPossessionDetail(CMap param);

    @Select("select a.* from (select c.contractpkey, c.contractstatus, o.ownername, u.usergroup, u.username, p.possessionkind, t.townname, oe.officialaddress, oe.officialnomination, oe.officiallandprice, oe.officiallandmeter, oe.officialbuildingprice, oe.officialbuildingmeter, " +
            "p.possessionmeter, p.possessionpurchaseprice, p.beforeprice, p.afterprice,  c.priority,  c.startprice,  (c.startprice + c.ingprice + c.endprice ) totalprice  from contract c join possession p on c.possessionpkey = p.possessionpkey\n" +
            "join owner o on p.ownerpkey = o.ownerpkey join officialestate oe on p.officialestatepkey = oe.officialestatepkey\n" +
            "join town t on oe.townpkey = t.townpkey  \n" +
            "join user u on c.userpkey = u.userpkey where u.userpkey = #{cuserpkey}) a, user u\n" +
            "where u.userpkey = #{cuserpkey} and u.rolepkey = 3 and u.userstatus = 'NORMAL'")
    List<CMap> listContractByAgency(CParam param);

    @Select("select te.* from team te, user u where u.rolepkey  in (-1, 1) and u.userpkey = #{cuserpkey} and te.teamkind = 'HQ' ")
    List<CMap> listTeamByHq(CParam param);

    @Select("select te.* from team te where te.rel = #{teampkey} and te.teamkind = 'TEAM'")
    List<CMap> listTeamByT(CMap param);

    @Select("select v.*,  o.ownerpkey, o.ownername, u.username from vote v join owner o on  v.ownerpkey = o.ownerpkey left join contract c on o.ownerpkey = c.ownerpkey left join user u on  c.userpkey = u.userpkey , (select rolepkey from user where userpkey = #{cuserpkey} and userstatus = 'NORMAL' and rolepkey in (1)) a where a.rolepkey in (1) ")
    List<CMap> listVote(CParam param);


    @Select("select * from vote v, (select rolepkey from user where userpkey = #{cuserpkey} and userstatus = 'NORMAL' and rolepkey in (1) ) a where v.ownerpkey = #{ownerpkey} and a.rolepkey in (1)")
    List<CMap> listVoteByOwner(CParam param);

    @Select("select u.username, o.ownergroup, o.ownerpkey, o.ownername, t.hqname, t.teamname from user u, contract c, owner o, (select t.teampkey hqpkey, t.teamname hqname,  t.teamkind hqkind , a.* from team t,  (select t.teampkey, t.rel, t.teamname, t.teamkind from team t where  t.teampkey and t.teamkind = 'TEAM') a where t.teampkey = a.rel) t, (select rolepkey from user where userpkey = #{cuserpkey} and userstatus = 'NORMAL' and rolepkey in (1) ) a where u.userpkey = c.userpkey and c.ownerpkey = o.ownerpkey and u.teampkey = t.teampkey and a.rolepkey in (1)")
    List<CMap> listWorkPlan(CParam param);




    @Select("select p.possessionpkey, p.ownerpkey, t.townname, oe.officialaddress, oe.officialkind, oe.officialmeter from possession p, officialestate oe, town t where p.officialestatepkey = oe.officialestatepkey and oe.townpkey = t.townpkey and p.ownerpkey = #{ownerpkey} limit 1")
    CMap getOwnerEstateAddress(CMap param);

    @Select("select  t.townname, oe.officialaddress, oe.officialkind, oe.officialmeter, p.possessionmeter, p.beforeprice, p.afterprice, c.regprice from contract c, contractpossession cp , possession p, officialestate oe, town t where c.contractpkey = cp.contractpkey and cp.possessionpkey = p.possessionpkey and p.officialestatepkey = oe.officialestatepkey and oe.townpkey = t.townpkey and  c.ownerpkey = #{ownerpkey} limit 1")
    CMap getOwnerByContractAndPossession(CMap map);





    @Select("select p.* from possession p, user u where p.possessionpkey = #{possessionpkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)")
    CMap getPossession(CParam param);

    @Select("select * from officialestate oe, town t  where  oe.townpkey = t.townpkey and oe.officialestatepkey = #{officialestatepkey} ")
    CMap getPossessionByEstate(CMap param);


    @Select("select o.* from possession p , owner o  where p.ownerpkey = o.ownerpkey and p.possessionpkey = #{possessionpkey}")
    CMap getPossessionByOwner(CMap param);


    @Select("select oe.*, t.*, a.*, p.* from officialestate oe, town t, area a , province p, user u  where oe.townpkey = t.townpkey and t.areapkey = a.areapkey and a.provincepkey = p.provincepkey and  officialestatepkey = #{officialestatepkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)")
    CMap getEstate(CParam param);


    @Select("select o.*, a.*, p.* from owner o, area a, province p, user u where o.areapkey = a.areapkey and a.provincepkey = p.provincepkey and  o.ownerpkey = #{ownerpkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)")
    CMap getOwner(CParam param);


    @Select("select a.*, te.teampkey hqpkey, te.teamname hqname from (select u.userpkey, u.username, u.userid, u.userstatus, u.usergroup, u.userphone, u.usercreatedate, u.rolepkey, te.teampkey, te.teamname, te.rel  from user u left join team te on u.teampkey = te.teampkey , \n" +
            "(select * from user where userpkey = #{cuserpkey} and userstatus = 'NORMAL' and rolepkey = -1) a where u.userpkey = #{userpkey} and a.userpkey = #{cuserpkey}) a left join team te on a.rel = te.teampkey")
    CMap getUser(CParam param);


    @Select("select me.*, mee.* from mortgage me, mortgagee mee, user u  where me.mortgageepkey = mee.mortgageepkey and me.mortgagepkey = #{mortgagepkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)")
    CMap getMortgage(CParam param);


    @Select("select mee.* from mortgagee mee, user u where mee.mortgageepkey = #{mortgageepkey} and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey in (1)")
    CMap getMortgagee(CParam param);





    @Select("select c.*, u.userpkey,  u.username, u.userphone,  t.hqpkey, t.hqname, t.teampkey, t.teamname from contract c, user u,  (select rolepkey from user u where u.userpkey = #{cuserpkey} and u.rolepkey in (1) and u.userstatus = 'NORMAL' ) a , (select t.teampkey hqpkey, t.teamname hqname,  t.teamkind hqkind , a.* from team t,  (select t.teampkey, t.rel, t.teamname, t.teamkind from team t where  t.teampkey and t.teamkind = 'TEAM') a where t.teampkey = a.rel)  t where c.contractpkey = #{contractpkey} and  c.userpkey = u.userpkey and u.teampkey = t.teampkey and a.rolepkey in (1)  ")
    CMap getContract(CParam param);





    @Select("select a.* from \n" +
            "(select o.ownername, o.owneraddress, o.ownerphone, o.ownertel, o.owneridnumber, \n" +
            "p.*, t.townname, oe.officiallandprice, oe.officiallandmeter , oe.officialbuildingprice, oe.officialbuildingmeter,  oe.officialaddress, c.priority, c.contractstatus, c.memo, c.startprice, c.startdate, c.ingprice, c.ingdate, c.endprice, c.enddate, c.contractpkey,\n" +
            "u.userpkey, u.usergroup, u.username, u.userid, u.userphone,\n" +
            "(c.startprice + c.ingprice + c.endprice ) totalprice  from contract c join possession p on c.possessionpkey = p.possessionpkey\n" +
            "join owner o on p.ownerpkey = o.ownerpkey join officialestate oe on p.officialestatepkey = oe.officialestatepkey\n" +
            "join town t on oe.townpkey = t.townpkey  \n" +
            "join user u on c.userpkey = u.userpkey where c.contractpkey = #{contractpkey} and u.userpkey = #{cuserpkey}) a, user u\n" +
            "where u.userpkey = #{cuserpkey} and u.rolepkey = 3 and u.userstatus = 'NORMAL'")
    CMap getContractByAgency(CParam param);

    @Select("select me.*, mee.* from mortgage me, mortgagee mee, possession p, contract c, user u where c.possessionpkey = p.possessionpkey and me.possessionpkey = p.possessionpkey and mee.mortgageepkey = me.mortgageepkey and c.contractpkey = 1 and u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 3")
    List<CMap> listMortgageByContract(CParam param);


    @Select("select sum(c.contractstatus = 'REG') regcnt, sum(c.contractstatus  = 'START') startcnt, sum(c.contractstatus  = 'MANAGE') managecnt , sum(c.contractstatus  = 'PENDING') pendingcnt, sum(c.contractstatus  = 'DONE') donecnt, sum(c.contractstatus  = 'FINISH') finishcnt from contract c, user u where u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1")
    CMap getContractStatusCntByAdmin(CParam param);

    @Select("select sum(c.startprice) startsum, sum(c.ingprice) ingsum, sum(c.endprice) endsum  from contract c, user u where u.userpkey = #{cuserpkey} and u.userstatus = 'NORMAL' and u.rolepkey = 1")
    CMap getContractPriceSumByAdmin(CParam param);

    @Select("select  count(o.ownerpkey) ownercnt, count(c.contractpkey) contractcnt from owner o  left join contract c  on o.ownerpkey = c.ownerpkey")
    CMap getOwnerAndContractCnt(CParam param);

    @Select("select * from (select count(*) estatecnt from officialestate oe where oe.officialkind = 'LAND') a, (select count(*) contractcnt from contract) b")
    CMap getEstateKindLandAndContractCnt(CParam param);

    @Select("select * from (select sum(oe.officialmeter) estatemetersum from officialestate oe where oe.officialkind = 'LAND') a, (select sum(p.possessionmeter) possessionmetersum  from contract c, contractpossession cp, possession p, officialestate oe where c.contractpkey = cp.contractpkey and cp.possessionpkey = p.possessionpkey and p.officialestatepkey = oe.officialestatepkey and oe.officialkind = 'LAND') b")
    CMap getEstateKindLandMeterAndContractCnt(CParam param);

    @Select("select * from (select sum(beforeprice) beforepricesum from possession) a, (select sum(p.beforeprice) cbeforepricesum from contract c, contractpossession cp , possession p where c.contractpkey = cp.contractpkey and cp.possessionpkey = p.possessionpkey) b")
    CMap getPossessionBeforePriceAndContract(CParam param);

    @Select("select sum(c.regprice) regpricesum, 350000000000 totalregprice from contract c, contractpossession cp, possession p where c.contractpkey = cp.contractpkey and cp.possessionpkey = p.possessionpkey")
    CMap getContractRegPriceSum(CParam param);

    @Select("select * from (select count(*) votecnt from vote) a, (select  count(v.votepkey) cvotecnt  from contract c, owner o, vote v where c.ownerpkey = o.ownerpkey and o.ownerpkey = v.ownerpkey) b ")
    CMap getVoteAndContractCnt(CParam param);

    @Select("select * from  (select sum( case when  g.ownergroup != '' then 1 else 0 end   ) ownergroupcnt from  (select o.ownergroup    from owner o group by o.ownergroup) g) a , (select sum( case when  g.ownergroup != '' then 1 else 0 end   )  cownergroupcnt from  (select o.ownergroup from owner o, contract c where o.ownerpkey = c.ownerpkey group by o.ownergroup) g) b")
    CMap getOwnerGroupCnt(CParam param);

    @Select("select b.*, a.*, case when c.doneteam is null then 0 else c.doneteam end  doneteamcnt from  (select t.*, count(t.teampkey) teamusercnt from user u, team t where u.teampkey = t.teampkey group by t.teampkey) a join (select t.teampkey hqpkey, t.teamname hqname,  t.teamkind hqkind , a.* from team t,  (select t.teampkey, t.rel, t.teamname, t.teamkind from team t where  t.teampkey and t.teamkind = 'TEAM') a where t.teampkey = a.rel group by hqpkey) b on a.rel =  b.hqpkey left join (select t.*, count( t.teampkey) doneteam from contract c, user u, team t  where c.contractstatus = 'DONE' and  c.userpkey = u.userpkey and u.teampkey = t.teampkey group by teampkey) c on a.teampkey = c.teampkey")
    List<CMap> listTeamContactCnt(CParam param);






    @Select("select t.hqname , t.teamname, u.username, u.userid, u.userphone from owner o left join contract c on o.ownerpkey = c.ownerpkey left join user u on c.userpkey = u.userpkey left join (select t.teampkey hqpkey, t.teamname hqname,  t.teamkind hqkind , a.* from team t,  (select t.teampkey, t.rel, t.teamname, t.teamkind from team t where  t.teampkey and t.teamkind = 'TEAM') a where t.teampkey = a.rel) t on u.teampkey = t.teampkey where o.ownerpkey = #{ownerpkey}")
    CMap getUserByOwner(CParam param);





}
