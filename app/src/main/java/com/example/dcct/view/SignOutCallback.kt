package com.example.dcct.view

import com.example.dcct.base.BaseCallback
import com.example.dcct.bean.BackResultData

interface SignOutCallback : BaseCallback {
    fun onLoadSignOutSuccess(backResultData: BackResultData<*>)
}