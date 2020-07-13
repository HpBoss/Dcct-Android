package com.example.dcct.model.impl

import com.example.dcct.base.BaseObserver
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.CoverEntity
import com.example.dcct.internet.NetWorkApi
import com.example.dcct.model.CoverModel

class CoverModelImp: CoverModel {
    override fun requestImageUrl(onReturnListener: CoverModel.OnReturnListener) {
        val internetAPI = NetWorkApi.instance!!.service
        val compose = internetAPI.imageUrl().compose(NetWorkApi.applySchedulers(
                object : BaseObserver<BackResultData<List<CoverEntity>>>() {
                    override fun onSuccess(t: BackResultData<List<CoverEntity>>) {
                        onReturnListener.onComplete(t.data)
                    }

                    override fun onFailure(e: Throwable) {
                        e.message?.let { onReturnListener.onError(it) }
                    }

                }
        ))
    }
}