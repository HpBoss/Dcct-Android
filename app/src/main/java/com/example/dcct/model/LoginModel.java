package com.example.dcct.model;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.LoginUserEntity;
import com.example.dcct.bean.UserEntity;
import com.example.dcct.view.LoginCallback;

public interface LoginModel {
    void postLoginData(LoginUserEntity loginUserEntity,ReturnLoginData returnLoginData);
    interface ReturnLoginData{
        void onComplete(BackResultData<UserEntity> backData);
        void onError(String msg);
    }
}
