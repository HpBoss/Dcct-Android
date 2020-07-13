package com.example.dcct.bean

class RegisterUserEntity(var nickname: String, var email: String, var password: String) {
    override fun toString(): String {
        return "RegisterUserEntity{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}'
    }

}