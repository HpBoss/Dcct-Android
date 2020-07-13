package com.example.dcct.bean

class LoginUserEntity(var email: String, var password: String) {
    override fun toString(): String {
        return "LoginUserEntity{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}'
    }

}