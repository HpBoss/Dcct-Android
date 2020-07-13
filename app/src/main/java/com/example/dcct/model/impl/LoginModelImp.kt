package com.example.dcct.model.impl

import android.util.Log
import com.example.dcct.base.BaseObserver
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.LoginUserEntity
import com.example.dcct.bean.UserEntity
import com.example.dcct.internet.NetWorkApi.Companion.applySchedulers
import com.example.dcct.internet.NetWorkApi.Companion.instance
import com.example.dcct.model.LoginModel
import com.example.dcct.model.LoginModel.ReturnLoginData

class LoginModelImp : LoginModel {
    override fun postLoginData(loginUserEntity: LoginUserEntity, returnLoginData: ReturnLoginData) {
        val internetAPI = instance!!.service
        //ResponseBody
        val task = internetAPI.submitLoginData(loginUserEntity)
                ?.compose(applySchedulers(object : BaseObserver<BackResultData<UserEntity>>() {
                    override fun onSuccess(t: BackResultData<UserEntity>) {
                        Log.d(TAG, "登录 ==》$t")
                        returnLoginData.onComplete(t)
                    }

                    override fun onFailure(e: Throwable) {
                        e.message?.let { returnLoginData.onError(it) }
                    }
                }))
    }

    companion object {
        private const val TAG = "LoginPresenterImp"
    }
}