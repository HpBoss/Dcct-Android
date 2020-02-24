package com.example.dcct.view;

import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.QueryResultEntity;
import com.example.dcct.model.internet.model.Record;

import java.util.List;

public interface RecordCallback {
    void onLoadQueryData(BackResultData<List<Record>> backResultData);
    void onLoadError(Throwable t);
}
