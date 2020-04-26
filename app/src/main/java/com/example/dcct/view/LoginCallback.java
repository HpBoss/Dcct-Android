package com.example.dcct.view;

import com.example.dcct.base.BaseCallback;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.TokenEntity;
import com.example.dcct.bean.UserEntity;

public interface LoginCallback extends BaseCallback {
    void onLoadLoginData(BackResultData<TokenEntity> backData);
}
