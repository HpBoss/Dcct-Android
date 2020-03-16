package com.example.dcct.presenter;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.model.internet.LoginUserEntity;
import com.example.dcct.view.LoginCallback;

public interface LoginPresenter extends RegisterAndUnRegister<LoginCallback>{
    void postLoginData(LoginUserEntity loginUserEntity);
}
