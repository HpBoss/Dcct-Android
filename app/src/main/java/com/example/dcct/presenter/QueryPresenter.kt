package com.example.dcct.presenter

import com.example.dcct.base.BasePresenter
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.PostQueryEntity
import com.example.dcct.bean.QueryResultEntity
import com.example.dcct.model.impl.QueryModelImp
import com.example.dcct.model.QueryModel
import com.example.dcct.model.QueryModel.ReturnQueryResult
import com.example.dcct.view.QueryCallback

class QueryPresenter : BasePresenter<QueryCallback>() {
    private val mQueryModel: QueryModel = QueryModelImp()
    fun fetchQueryResult(postQueryEntity: PostQueryEntity) {
        if (mCallBackView!!.get() != null) {
            mQueryModel.postQueryInformation(postQueryEntity, object : ReturnQueryResult {
                override fun onComplete(listBackResultData: BackResultData<List<QueryResultEntity>>) {
                    mCallBackView!!.get()!!.onLoadQueryData(listBackResultData)
                }

                override fun onError(msg: String) {
                    mCallBackView!!.get()!!.showErrorMsg(msg)
                }
            })
        }
    }
}