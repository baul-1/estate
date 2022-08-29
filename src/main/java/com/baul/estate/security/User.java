package com.baul.estate.security;

public class User {

    String pkey;
    String name;

    String userPkey="0";

    public User(){}

    public User(String name){
        this.name = name;
    }

    public void setUserPkey(String userPkey) {
        this.userPkey = userPkey;
    }


    public String getUserPkey(){
        return userPkey;
    }



    public String getPkey(){return this.pkey;}

    public String getName(){return this.name;}

    public String getType(){
        return "COMPANY";
    }

}
