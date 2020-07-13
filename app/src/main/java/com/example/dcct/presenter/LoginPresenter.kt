package com.example.dcct.presenter

import com.example.dcct.base.BasePresenter
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.LoginUserEntity
import com.example.dcct.bean.UserEntity
import com.example.dcct.model.impl.LoginModelImp
import com.example.dcct.model.LoginModel
import com.example.dcct.model.LoginModel.ReturnLoginData
import com.example.dcct.view.LoginCallback

class LoginPresenter : BasePresenter<LoginCallback>() {
    private val mLoginModel: LoginModel = LoginModelImp()
    fun fetchUserEntity(loginUserEntity: LoginUserEntity) {
        if (mCallBackView!!.get() != null) {
            mLoginModel.postLoginData(loginUserEntity, object : ReturnLoginData {
                override fun onComplete(backData: BackResultData<UserEntity>) {
                    mCallBackView!!.get()!!.onLoadLoginData(backData)
                }

                override fun onError(msg: String) {
                    mCallBackView!!.get()!!.showErrorMsg(msg)
                }
            })
        }
    }
}