package com.example.dcct.model;

import com.example.dcct.bean.CoverEntity;

import java.util.List;

public interface CoverModel {
    void requestImageUrl(OnReturnListener onReturnListener);
    interface OnReturnListener{
        void onComplete(List<CoverEntity> coverEntities);
        void onError(String msg);
    }
}
