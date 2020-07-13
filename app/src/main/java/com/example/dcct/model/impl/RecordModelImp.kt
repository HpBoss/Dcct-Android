package com.example.dcct.model.impl

import com.example.dcct.base.BaseObserver
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.Record
import com.example.dcct.internet.NetWorkApi.Companion.applySchedulers
import com.example.dcct.internet.NetWorkApi.Companion.instance
import com.example.dcct.model.RecordModel
import com.example.dcct.model.RecordModel.ReturnQueryRecord

class RecordModelImp : RecordModel {
    override fun getAllQueryRecord(id: Long, returnQueryRecord: ReturnQueryRecord) {
        val internetApi = instance!!.service
        val compose = internetApi.getRecords(id)
                .compose(applySchedulers(object : BaseObserver<BackResultData<List<Record>>>() {
                    override fun onSuccess(t: BackResultData<List<Record>>) {
//                        Log.d( TAG, "个人查询记录 ==》" + listBackResultData.toString() );
                        returnQueryRecord.onComplete(t)
                    }

                    override fun onFailure(e: Throwable) {
                        e.message?.let { returnQueryRecord.onError(it) }
                    }
                }))
    }

    companion object {
        private const val TAG = "RecordPresenterImp"
    }
}