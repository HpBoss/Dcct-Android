package com.example.dcct.view;

import com.example.dcct.base.BaseCallback;
import com.example.dcct.bean.CoverEntity;

import java.util.List;

public interface ImageUrlCallback extends BaseCallback {
    void onLoadImageUrl(List<CoverEntity> coverEntities);
}
