package com.example.dcct.view;

import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.UserEntity;

public interface LoginCallback {

    void onLoadLoginData(BackResultData<UserEntity> backData);

}
