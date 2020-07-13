package com.example.dcct.internet

object Constant {
    //虚拟机ip：10.0.2.2
    const val BASE_URL = "http://47.102.206.167:8080/"

    //    static final String BASE_URL = "http://10.0.2.2:8080/";
    const val LOGIN_API = "login"
    const val REGISTER_API = "user/register"
    const val COVER_API = "getAllCover"
    const val QUERY_API = "query"
    const val RECORD_API = "record/{uid}"
    const val SIGN_OUT = "sign_out/{uid}"
}