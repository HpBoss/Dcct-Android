package com.example.dcct.view;

import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.model.internet.Record;

import java.util.List;

public interface RecordCallback {
    void onLoadQueryData(BackResultData<List<Record>> backResultData);
    void onLoadError(Throwable t);
}
