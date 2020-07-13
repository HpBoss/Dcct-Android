package com.example.dcct.model

import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.CoverEntity

interface CoverModel {
    fun requestImageUrl(onReturnListener: OnReturnListener)
    interface OnReturnListener {
        fun onComplete(coverEntities: List<CoverEntity>)
        fun onError(msg: String)
    }
}