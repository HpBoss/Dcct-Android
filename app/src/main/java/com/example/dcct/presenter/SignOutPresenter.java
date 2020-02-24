package com.example.dcct.presenter;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.view.SignOutCallback;

public interface SignOutPresenter extends RegisterAndUnRegister<SignOutCallback> {
    void postSignOutId(long uid);
}
