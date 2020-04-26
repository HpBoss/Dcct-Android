package com.example.dcct.internet;

public class Constant {
    //虚拟机ip：10.0.2.2
    static final String BASE_URL = "http://47.102.206.167:8080/";
//    static final String BASE_URL = "http://10.0.2.2:8080/";
    static final String LOGIN_API = "sign/signIn";
    static final String REGISTER_API = "user/register";
    static final String COVER_API ="getAllCover";
    static final String QUERY_API = "query";
    static final String RECORD_API = "record/{uid}";
    static final String SIGN_OUT = "sign_out/{uid}";
}
