package com.example.dcct.model;

import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.RegisterUserEntity;

public interface RegisterModel{
    void postRegisterInfor(RegisterUserEntity registerUserEntity,ReturnRegisterResult returnRegisterResult);
    interface ReturnRegisterResult{
        void onComplete(BackResultData backResultData);
        void onError(String msg);
    }

}
