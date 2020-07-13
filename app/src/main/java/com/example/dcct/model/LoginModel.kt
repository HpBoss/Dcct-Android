package com.example.dcct.model

import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.LoginUserEntity
import com.example.dcct.bean.UserEntity

interface LoginModel {
    fun postLoginData(loginUserEntity: LoginUserEntity, returnLoginData: ReturnLoginData)
    interface ReturnLoginData {
        fun onComplete(backData: BackResultData<UserEntity>)
        fun onError(msg: String)
    }
}