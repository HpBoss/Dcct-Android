package com.example.dcct.model;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.RegisterUserEntity;
import com.example.dcct.view.RegisterCallback;

public interface RegisterModel{
    void postRegisterInfor(RegisterUserEntity registerUserEntity,ReturnRegisterResult returnRegisterResult);
    interface ReturnRegisterResult{
        void onComplete(BackResultData backResultData);
        void onError(String msg);
    }

}
