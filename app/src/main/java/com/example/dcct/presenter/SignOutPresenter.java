package com.example.dcct.presenter;

import com.example.dcct.base.BasePresenter;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.model.Impl.SignOutModelImp;
import com.example.dcct.model.SignOutModel;
import com.example.dcct.view.SignOutCallback;

public class SignOutPresenter extends BasePresenter {
    private SignOutModel mSignOutModel = new SignOutModelImp();

    public void fetchSignState(long uid){
        if (mSignOutModel !=null && mCallBackView.get() != null) {
            mSignOutModel.postSignOutId( uid, new SignOutModel.ReturnSignState() {
                @Override
                public void onComplete(BackResultData backResultData) {
                    ((SignOutCallback)mCallBackView.get()).onLoadSignOutSuccess( backResultData );
                }

                @Override
                public void onError(String msg) {
                    ((SignOutCallback)mCallBackView.get()).showErrorMsg( msg );
                }
            } );
        }
    }
}
