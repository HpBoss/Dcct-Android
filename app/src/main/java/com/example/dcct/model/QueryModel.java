package com.example.dcct.model;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.PostQueryEntity;
import com.example.dcct.bean.QueryResultEntity;
import com.example.dcct.view.QueryCallback;

import java.util.List;

public interface QueryModel{
    void postQueryInformation(PostQueryEntity postQueryEntity,ReturnQueryResult returnQueryResult);
    interface ReturnQueryResult{
        void onComplete(BackResultData<List<QueryResultEntity>> listBackResultData);
        void onError(String msg);
    }
}
