package com.example.dcct.bean

class UserEntity {
    var uid = 0
    var password: Any? = null
    var nickname: String? = null
    var email: String? = null
    override fun toString(): String {
        return "UserEntity{" +
                "uid=" + uid +
                ", password=" + password +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}'
    }

}