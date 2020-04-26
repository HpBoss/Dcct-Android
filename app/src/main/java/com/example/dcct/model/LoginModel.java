package com.example.dcct.model;

import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.LoginUserEntity;
import com.example.dcct.bean.TokenEntity;
import com.example.dcct.bean.UserEntity;

public interface LoginModel {
    void postLoginData(LoginUserEntity loginUserEntity,ReturnLoginData returnLoginData);
    interface ReturnLoginData{
        void onComplete(BackResultData<TokenEntity> backData);
        void onError(String msg);
    }
}
