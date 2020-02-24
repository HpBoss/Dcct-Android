package com.example.dcct.presenter;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.view.ImageUrlCallback;


public interface CoverPresenter extends RegisterAndUnRegister<ImageUrlCallback> {
    void requestImageUrl();
}
