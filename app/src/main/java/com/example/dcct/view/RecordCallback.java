package com.example.dcct.view;

import com.example.dcct.base.BaseCallback;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.Record;

import java.util.List;

public interface RecordCallback extends BaseCallback {
    void onLoadQueryData(BackResultData<List<Record>> backResultData);
    void onLoadError(Throwable t);
}
