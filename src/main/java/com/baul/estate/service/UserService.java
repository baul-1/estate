package com.baul.estate.service;

import com.baul.estate.config.CMap;
import com.baul.estate.config.CParam;
import com.baul.estate.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    @Transactional
    public void addContract(CParam param){

        int result = userMapper.addContract(param);

        if(result != 1) throw  new RuntimeException("addContractError");

        System.out.println(param.getS("contractpkey"));

        String possessionList = param.getS("possessionlist");

        ObjectMapper mapper = new ObjectMapper();

        try {
            CMap[] p = mapper.readValue(possessionList, CMap[].class);

            for (Map possession : p) {
                param.putAll(possession);
                userMapper.addContractPossession(param);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException("addContractPossessionError");
        }

    }

    @Transactional
    public void modifyContract(CParam param){

        int result = userMapper.modifyContractByAdmin(param);

        if(result != 1) throw  new RuntimeException("modifyContractError");

        System.out.println(result);

        String addPossessionList = param.getS("addpossessionlist");
        String deletePossessionList = param.getS("deletepossessionlist");

        ObjectMapper mapper = new ObjectMapper();

        try {
            CMap[] ap = mapper.readValue(addPossessionList, CMap[].class);
            CMap[] dp = mapper.readValue(deletePossessionList, CMap[].class);

            if("Y".equals(param.getS("isadd"))){
                for (CMap possession : ap) {
                    param.putAll(possession);
                    userMapper.addContractPossession(param);
                }
            }

            if("Y".equals(param.getS("isdelete"))){
                for (CMap possession : dp) {
                    param.putAll(possession);
                    userMapper.deleteContractPossession(param);
                }
            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException("addOrDeleteContractPossessionError");
        }

    }

    @Transactional
    public void addMortgage(CParam param){
       int result =  userMapper.addMortgage(param);

       if(result != 1) throw  new RuntimeException("addMortgageError");

       String possessionList = param.getS("possessionlist");

       ObjectMapper  mapper = new ObjectMapper();

       try{
           CMap [] p = mapper.readValue(possessionList, CMap[].class);

           for (CMap possession : p) {
               param.putAll(possession);
               userMapper.addMortgagePossession(param);
           }

       }catch (JsonProcessingException e){
           throw new RuntimeException("addMortgagePossessionError");

       }

    }

    @Transactional
    public void modifyMortgage(CParam param){
        int result =  userMapper.modifyMortgage(param);

        if(result != 1) throw  new RuntimeException("modifyMortgageError");

        String addPossessionList = param.getS("addpossessionlist");
        String deletePossessionList = param.getS("deletepossessionlist");

        ObjectMapper  mapper = new ObjectMapper();

        try{
            CMap [] ap = mapper.readValue(addPossessionList, CMap[].class);
            CMap [] dp = mapper.readValue(deletePossessionList, CMap[].class);

            if("Y".equals(param.getS("isadd"))){
                for (CMap possession : ap) {
                    param.putAll(possession);
                    userMapper.addMortgagePossession(param);
                }
            }

            if("Y".equals(param.getS("isdelete"))){
                for (CMap possession : dp) {
                    param.putAll(possession);
                    userMapper.deleteMortgagePossession(param);
                }
            }


        }catch (JsonProcessingException e){
            throw new RuntimeException("addMortgagePossessionError");
        }

    }





}
