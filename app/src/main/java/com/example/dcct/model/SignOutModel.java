package com.example.dcct.model;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.view.SignOutCallback;

public interface SignOutModel {
    void postSignOutId(long uid,ReturnSignState returnSignState);
    interface ReturnSignState{
        void onComplete(BackResultData backResultData);
        void onError(String msg);
    }
}
