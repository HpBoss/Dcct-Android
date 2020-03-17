package com.example.dcct.presenter;

import com.example.dcct.base.BasePresenter;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.RegisterUserEntity;
import com.example.dcct.model.Impl.RegisterModelImp;
import com.example.dcct.model.RegisterModel;
import com.example.dcct.view.RegisterCallback;

public class RegisterPresenter extends BasePresenter {
    private RegisterModel mRegisterModel = new RegisterModelImp();

    public void fetchRegisterResult(RegisterUserEntity registerUserEntity){
        if (mRegisterModel != null && mCallBackView.get() != null) {
            mRegisterModel.postRegisterInfor( registerUserEntity, new RegisterModel.ReturnRegisterResult() {
                @Override
                public void onComplete(BackResultData backResultData) {
                    ((RegisterCallback)mCallBackView.get()).onLoadRegisterData( backResultData );
                }

                @Override
                public void onError(String msg) {
                    ((RegisterCallback)mCallBackView.get()).showErrorMsg( msg );
                }
            } );
        }
    }
}
