package com.example.dcct.model.impl

import com.example.dcct.base.BaseObserver
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.PostQueryEntity
import com.example.dcct.bean.QueryResultEntity
import com.example.dcct.internet.NetWorkApi.Companion.applySchedulers
import com.example.dcct.internet.NetWorkApi.Companion.instance
import com.example.dcct.model.QueryModel
import com.example.dcct.model.QueryModel.ReturnQueryResult

class QueryModelImp : QueryModel {
    override fun postQueryInformation(postQueryEntity: PostQueryEntity, returnQueryResult: ReturnQueryResult) {
        val internetApi = instance!!.service
        val compose = internetApi.submitQueryData(postQueryEntity)
                ?.compose(applySchedulers(object : BaseObserver<BackResultData<List<QueryResultEntity>>>() {
                    override fun onSuccess(t: BackResultData<List<QueryResultEntity>>) {
//                        Log.d( TAG, "查询结果 ==》" + listBackResultData.toString() );
                        returnQueryResult.onComplete(t)
                    }

                    override fun onFailure(e: Throwable) {
                        e.message?.let { returnQueryResult.onError(it) }
                    }
                }))
    }

    companion object {
        private const val TAG = "QueryPresenterImp"
    }
}