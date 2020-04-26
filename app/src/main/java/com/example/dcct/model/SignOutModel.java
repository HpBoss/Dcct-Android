package com.example.dcct.model;

import com.example.dcct.bean.BackResultData;

public interface SignOutModel {
    void postSignOutId(long uid,ReturnSignState returnSignState);
    interface ReturnSignState{
        void onComplete(BackResultData backResultData);
        void onError(String msg);
    }
}
