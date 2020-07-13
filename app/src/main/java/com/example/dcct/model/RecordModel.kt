package com.example.dcct.model

import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.Record

interface RecordModel {
    fun getAllQueryRecord(id: Long, returnQueryRecord: ReturnQueryRecord)
    interface ReturnQueryRecord {
        fun onComplete(listBackResultData: BackResultData<List<Record>>)
        fun onError(msg: String)
    }
}