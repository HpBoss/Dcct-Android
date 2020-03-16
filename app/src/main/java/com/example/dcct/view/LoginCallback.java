package com.example.dcct.view;

import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.model.internet.UserEntity;

public interface LoginCallback {

    void onLoadLoginData(BackResultData<UserEntity> backData);

}
