package com.example.dcct.model

import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.PostQueryEntity
import com.example.dcct.bean.QueryResultEntity

interface QueryModel {
    fun postQueryInformation(postQueryEntity: PostQueryEntity, returnQueryResult: ReturnQueryResult)
    interface ReturnQueryResult {
        fun onComplete(listBackResultData: BackResultData<List<QueryResultEntity>>)
        fun onError(msg: String)
    }
}