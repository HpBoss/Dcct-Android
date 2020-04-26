package com.example.dcct.presenter;

import com.example.dcct.base.BasePresenter;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.LoginUserEntity;
import com.example.dcct.bean.TokenEntity;
import com.example.dcct.bean.UserEntity;
import com.example.dcct.model.Impl.LoginModelImp;
import com.example.dcct.model.LoginModel;
import com.example.dcct.view.LoginCallback;

public class LoginPresenter extends BasePresenter {
    private LoginModel mLoginModel = new LoginModelImp();

    public void fetchUserEntity(LoginUserEntity loginUserEntity){
        if (mLoginModel != null && mCallBackView.get() != null) {
            mLoginModel.postLoginData( loginUserEntity, new LoginModel.ReturnLoginData() {
                @Override
                public void onComplete(BackResultData<TokenEntity> backData) {
                    ((LoginCallback)mCallBackView.get()).onLoadLoginData( backData );
                }

                @Override
                public void onError(String msg) {
                    ((LoginCallback)mCallBackView.get()).showErrorMsg( msg );
                }
            } );
        }
    }
}
