package com.example.dcct.presenter;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.view.RecordCallback;

public interface RecordPresenter extends RegisterAndUnRegister<RecordCallback> {
    void getAllQueryRecord(long id);
}
