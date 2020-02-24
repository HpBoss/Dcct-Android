package com.example.dcct.view;

import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.QueryResultEntity;

import java.util.List;

public interface QueryCallback {
    void onLoadQueryData(BackResultData<List<QueryResultEntity>> backResultData);
}
