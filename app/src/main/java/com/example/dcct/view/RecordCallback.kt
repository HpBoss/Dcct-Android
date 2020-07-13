package com.example.dcct.view

import com.example.dcct.base.BaseCallback
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.Record

interface RecordCallback : BaseCallback {
    fun onLoadQueryData(backResultData: BackResultData<List<Record>>)
    fun onLoadError(t: Throwable)
}