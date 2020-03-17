package com.example.dcct.presenter;

import com.example.dcct.base.BasePresenter;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.Record;
import com.example.dcct.model.Impl.RecordModelImp;
import com.example.dcct.model.RecordModel;
import com.example.dcct.view.RecordCallback;

import java.util.List;

public class RecordPresenter extends BasePresenter {
    private RecordModel mRecordModel = new RecordModelImp();

    public void fetchQueryRecord(long id){
        if (mRecordModel != null && mCallBackView.get() != null) {
            mRecordModel.getAllQueryRecord( id, new RecordModel.ReturnQueryRecord() {
                @Override
                public void onComplete(BackResultData<List<Record>> listBackResultData) {
                    ((RecordCallback)mCallBackView.get()).onLoadQueryData( listBackResultData );
                }

                @Override
                public void onError(String msg) {
                    ((RecordCallback)mCallBackView.get()).showErrorMsg( msg );
                }
            } );
        }
    }
}
