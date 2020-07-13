package com.example.dcct.presenter

import com.example.dcct.base.BasePresenter
import com.example.dcct.bean.BackResultData
import com.example.dcct.model.impl.SignOutModelImp
import com.example.dcct.model.SignOutModel
import com.example.dcct.model.SignOutModel.ReturnSignState
import com.example.dcct.view.SignOutCallback

class SignOutPresenter : BasePresenter<SignOutCallback>() {
    private val mSignOutModel: SignOutModel = SignOutModelImp()
    fun fetchSignState(uid: Long) {
        if (mCallBackView!!.get() != null) {
            mSignOutModel.postSignOutId(uid, object : ReturnSignState {
                override fun onComplete(backResultData: BackResultData<*>) {
                    mCallBackView!!.get()!!.onLoadSignOutSuccess(backResultData)
                }

                override fun onError(msg: String) {
                    mCallBackView!!.get()!!.showErrorMsg(msg)
                }
            })
        }
    }
}