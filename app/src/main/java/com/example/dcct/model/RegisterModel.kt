package com.example.dcct.model

import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.RegisterUserEntity

interface RegisterModel {
    fun postRegisterInfo(registerUserEntity: RegisterUserEntity, returnRegisterResult: ReturnRegisterResult)
    interface ReturnRegisterResult {
        fun onComplete(backResultData: BackResultData<*>)
        fun onError(msg: String)
    }
}