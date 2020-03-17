package com.example.dcct.view;

import com.example.dcct.base.BaseCallback;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.QueryResultEntity;

import java.util.List;

public interface QueryCallback extends BaseCallback {
    void onLoadQueryData(BackResultData<List<QueryResultEntity>> backResultData);
}
