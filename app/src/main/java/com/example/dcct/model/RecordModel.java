package com.example.dcct.model;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.Record;
import com.example.dcct.view.RecordCallback;

import java.util.List;

public interface RecordModel {
    void getAllQueryRecord(long id,ReturnQueryRecord returnQueryRecord);
    interface ReturnQueryRecord{
        void onComplete(BackResultData<List<Record>> listBackResultData);
        void onError(String msg);
    }
}
