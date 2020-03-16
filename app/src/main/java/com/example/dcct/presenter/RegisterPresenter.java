package com.example.dcct.presenter;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.model.internet.RegisterUserEntity;
import com.example.dcct.view.RegisterCallback;

public interface RegisterPresenter extends RegisterAndUnRegister<RegisterCallback> {

    void postRegisterInfor(RegisterUserEntity registerUserEntity);

}
