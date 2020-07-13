package com.example.dcct.presenter

import com.example.dcct.base.BasePresenter
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.CoverEntity
import com.example.dcct.model.CoverModel
import com.example.dcct.model.CoverModel.OnReturnListener
import com.example.dcct.model.impl.CoverModelImp
import com.example.dcct.view.ImageUrlCallback

class CoverPresenter : BasePresenter<ImageUrlCallback>() {
    private val mCoverModel: CoverModel = CoverModelImp()
    fun fetchImageUrl() {
        if (mCallBackView!!.get() != null) {
            mCoverModel.requestImageUrl(object : OnReturnListener {
                override fun onComplete(coverEntities: List<CoverEntity>) {
                    mCallBackView!!.get()!!.onLoadImageUrl(coverEntities)
                }

                override fun onError(msg: String) {
                    mCallBackView!!.get()!!.showErrorMsg(msg)
                }
            })
        }
    }
}