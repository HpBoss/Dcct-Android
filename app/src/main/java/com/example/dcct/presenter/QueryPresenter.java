package com.example.dcct.presenter;

import com.example.dcct.base.RegisterAndUnRegister;
import com.example.dcct.model.internet.PostQueryEntity;
import com.example.dcct.view.QueryCallback;

public interface QueryPresenter extends RegisterAndUnRegister<QueryCallback> {
    void postQueryInformation(PostQueryEntity postQueryEntity);
}
