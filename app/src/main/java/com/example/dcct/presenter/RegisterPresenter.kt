package com.example.dcct.presenter

import com.example.dcct.base.BasePresenter
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.RegisterUserEntity
import com.example.dcct.model.impl.RegisterModelImp
import com.example.dcct.model.RegisterModel
import com.example.dcct.model.RegisterModel.ReturnRegisterResult
import com.example.dcct.view.RegisterCallback

class RegisterPresenter : BasePresenter<RegisterCallback>() {
    private val mRegisterModel: RegisterModel = RegisterModelImp()
    fun fetchRegisterResult(registerUserEntity: RegisterUserEntity) {
        if (mCallBackView!!.get() != null) {
            mRegisterModel.postRegisterInfo(registerUserEntity, object : ReturnRegisterResult {
                override fun onComplete(backResultData: BackResultData<*>) {
                    mCallBackView!!.get()!!.onLoadRegisterData(backResultData)
                }

                override fun onError(msg: String) {
                    mCallBackView!!.get()!!.showErrorMsg(msg)
                }
            })
        }
    }
}