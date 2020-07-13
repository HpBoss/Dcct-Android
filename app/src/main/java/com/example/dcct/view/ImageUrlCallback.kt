package com.example.dcct.view

import com.example.dcct.base.BaseCallback
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.CoverEntity

interface ImageUrlCallback : BaseCallback {
    fun onLoadImageUrl(coverEntities: List<CoverEntity>)
}