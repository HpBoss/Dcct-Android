package com.example.dcct.model.impl

import com.example.dcct.base.BaseObserver
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.RegisterUserEntity
import com.example.dcct.internet.NetWorkApi.Companion.applySchedulers
import com.example.dcct.internet.NetWorkApi.Companion.instance
import com.example.dcct.model.RegisterModel
import com.example.dcct.model.RegisterModel.ReturnRegisterResult

class RegisterModelImp : RegisterModel {
    override fun postRegisterInfo(registerUserEntity: RegisterUserEntity, returnRegisterResult: ReturnRegisterResult) {
        val internetAPI = instance!!.service
        val compose = internetAPI.submitRegisterData(registerUserEntity)
                .compose(applySchedulers(object : BaseObserver<BackResultData<*>>() {
                    override fun onSuccess(t: BackResultData<*>) {
//                        Log.d( TAG, "注册 ==》" + backResultData.toString() );
                        returnRegisterResult.onComplete(t)
                    }

                    override fun onFailure(e: Throwable) {
                        e.message?.let { returnRegisterResult.onError(it) }
                    }
                }))
    }

    companion object {
        private const val TAG = "RegisterPresenterImp"
    }
}