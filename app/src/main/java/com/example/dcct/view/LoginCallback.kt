package com.example.dcct.view

import com.example.dcct.base.BaseCallback
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.UserEntity

interface LoginCallback : BaseCallback {
    fun onLoadLoginData(backData: BackResultData<UserEntity>)
}