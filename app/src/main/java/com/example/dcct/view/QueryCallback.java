package com.example.dcct.view;

import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.model.internet.QueryResultEntity;

import java.util.List;

public interface QueryCallback {
    void onLoadQueryData(BackResultData<List<QueryResultEntity>> backResultData);
}
