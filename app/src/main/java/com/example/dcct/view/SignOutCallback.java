package com.example.dcct.view;

import com.example.dcct.base.BaseCallback;
import com.example.dcct.bean.BackResultData;

public interface SignOutCallback extends BaseCallback {
    void onLoadSignOutSuccess(BackResultData backResultData);
}
