package com.example.dcct.view

import com.example.dcct.base.BaseCallback
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.QueryResultEntity

interface QueryCallback : BaseCallback {
    fun onLoadQueryData(backResultData: BackResultData<List<QueryResultEntity>>)
}