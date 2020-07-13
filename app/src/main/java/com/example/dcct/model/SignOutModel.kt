package com.example.dcct.model

import com.example.dcct.bean.BackResultData

interface SignOutModel {
    fun postSignOutId(uid: Long, returnSignState: ReturnSignState)
    interface ReturnSignState {
        fun onComplete(backResultData: BackResultData<*>)
        fun onError(msg: String)
    }
}