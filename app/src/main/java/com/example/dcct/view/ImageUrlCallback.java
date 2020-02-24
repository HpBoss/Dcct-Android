package com.example.dcct.view;

import com.example.dcct.model.internet.model.CoverEntity;

import java.util.List;

public interface ImageUrlCallback {
    void onLoadImageUrl(List<CoverEntity> coverEntities);
}
