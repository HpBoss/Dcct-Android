package com.example.dcct.presenter

import com.example.dcct.base.BasePresenter
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.Record
import com.example.dcct.model.impl.RecordModelImp
import com.example.dcct.model.RecordModel
import com.example.dcct.model.RecordModel.ReturnQueryRecord
import com.example.dcct.view.RecordCallback

class RecordPresenter : BasePresenter<RecordCallback>() {
    private val mRecordModel: RecordModel = RecordModelImp()
    fun fetchQueryRecord(id: Long) {
        if (mCallBackView!!.get() != null) {
            mRecordModel.getAllQueryRecord(id, object : ReturnQueryRecord {
                override fun onComplete(listBackResultData: BackResultData<List<Record>>) {
                    mCallBackView!!.get()!!.onLoadQueryData(listBackResultData)
                }

                override fun onError(msg: String) {
                    mCallBackView!!.get()!!.showErrorMsg(msg)
                }
            })
        }
    }
}