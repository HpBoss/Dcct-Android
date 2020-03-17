package com.example.dcct.presenter;

import com.example.dcct.base.BasePresenter;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.PostQueryEntity;
import com.example.dcct.bean.QueryResultEntity;
import com.example.dcct.model.Impl.QueryModelImp;
import com.example.dcct.model.QueryModel;
import com.example.dcct.view.QueryCallback;

import java.util.List;


public class QueryPresenter extends BasePresenter {
    private QueryModel mQueryModel = new QueryModelImp();

    public void fetchQueryResult(PostQueryEntity postQueryEntity){
        if (mQueryModel != null && mCallBackView.get() != null) {
            mQueryModel.postQueryInformation( postQueryEntity, new QueryModel.ReturnQueryResult() {
                @Override
                public void onComplete(BackResultData<List<QueryResultEntity>> listBackResultData) {
                    ((QueryCallback)mCallBackView.get()).onLoadQueryData( listBackResultData );
                }

                @Override
                public void onError(String msg) {
                    ((QueryCallback)mCallBackView.get()).showErrorMsg( msg );
                }
            } );
        }
    }
}
