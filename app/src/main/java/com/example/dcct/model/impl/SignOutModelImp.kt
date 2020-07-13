package com.example.dcct.model.impl

import com.example.dcct.base.BaseObserver
import com.example.dcct.bean.BackResultData
import com.example.dcct.internet.NetWorkApi.Companion.applySchedulers
import com.example.dcct.internet.NetWorkApi.Companion.instance
import com.example.dcct.model.SignOutModel
import com.example.dcct.model.SignOutModel.ReturnSignState

class SignOutModelImp : SignOutModel {
    override fun postSignOutId(uid: Long, returnSignState: ReturnSignState) {
        val internetApi = instance!!.service
        val compose = internetApi.subSignOutId(uid)
                .compose(applySchedulers(object : BaseObserver<BackResultData<*>>() {
                    override fun onSuccess(t: BackResultData<*>) {
//                        Log.d( TAG, "退出登录 ==》" + backResultData.toString() );
                        returnSignState.onComplete(t)
                    }

                    override fun onFailure(e: Throwable) {
                        e.message?.let { returnSignState.onError(it) }
                    }
                }))
    }

    companion object {
        private const val TAG = "SignOutPresenterImp"
    }
}