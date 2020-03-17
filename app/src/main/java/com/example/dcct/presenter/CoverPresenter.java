package com.example.dcct.presenter;

import com.example.dcct.base.BasePresenter;
import com.example.dcct.bean.CoverEntity;
import com.example.dcct.model.CoverModel;
import com.example.dcct.model.Impl.CoverModelImp;
import com.example.dcct.view.ImageUrlCallback;

import java.util.List;

public class CoverPresenter extends BasePresenter {
    private CoverModel mCoverModel = new CoverModelImp();

    public void fetchImageUrl(){
        if (mCoverModel != null && mCallBackView.get() != null) {
            mCoverModel.requestImageUrl( new CoverModel.OnReturnListener() {
                @Override
                public void onComplete(List<CoverEntity> coverEntities) {
                    ((ImageUrlCallback) mCallBackView.get()).onLoadImageUrl( coverEntities );
                }

                @Override
                public void onError(String msg) {
                    ((ImageUrlCallback) mCallBackView.get()).showErrorMsg( msg );
                }
            } );
        }
    }

}
