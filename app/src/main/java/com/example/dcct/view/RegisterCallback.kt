package com.example.dcct.view

import com.example.dcct.base.BaseCallback
import com.example.dcct.bean.BackResultData

interface RegisterCallback : BaseCallback {
    fun onLoadRegisterData(backData: BackResultData<*>)
}