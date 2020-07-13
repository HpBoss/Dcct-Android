package com.example.dcct.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dcct.R
import com.example.dcct.bean.*
import com.example.dcct.databinding.FragmentRecordBinding
import com.example.dcct.presenter.QueryPresenter
import com.example.dcct.presenter.RecordPresenter
import com.example.dcct.ui.activity.GaugingReportActivity
import com.example.dcct.ui.adapter.RecordHistoryAdapter
import com.example.dcct.ui.adapter.RecordHistoryAdapter.OnItemClickListener
import com.example.dcct.view.QueryCallback
import com.example.dcct.view.RecordCallback
import java.util.*

class RecordFragment : Fragment(), RecordCallback {
    private var mRecordList: List<Record> = ArrayList()
    private var mUid: Long = 0
    private var mQueryPresenter: QueryPresenter? = null
    private var mRecordPresenter: RecordPresenter? = null
    private var mBinding: FragmentRecordBinding? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentRecordBinding.inflate(layoutInflater)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //竖直流布局
        val linearLayoutManager = LinearLayoutManager(activity)
        mBinding!!.recordRecycleView.layoutManager = linearLayoutManager
        //获取历史记录
        val preferences = requireActivity()
                .getSharedPreferences("SHARE_APP_DATA", Context.MODE_PRIVATE)
        mUid = preferences.getLong("uid", 1)
        mRecordPresenter = RecordPresenter()
        mRecordPresenter!!.attachView(this)
        mRecordPresenter!!.fetchQueryRecord(mUid)
    }

    override fun onLoadQueryData(backResultData: BackResultData<List<Record>>) {
        if (backResultData.isState) {
            mRecordList = backResultData.data!!
            val recordHistoryAdapter = RecordHistoryAdapter(mRecordList)
            mBinding!!.recordRecycleView.adapter = recordHistoryAdapter
            recordHistoryAdapter.setOnClickItems(object : OnItemClickListener {
                override fun onClickItem(position: Int) {
                    val queryName = mRecordList[position].queryName
                    val drugOne = queryName.substring(0, queryName.lastIndexOf("、"))
                    val drugTwo = queryName.substring(queryName.lastIndexOf("、") + 1)
                    val postQueryEntity = PostQueryEntity(drugOne, drugTwo, mUid)
                    mQueryPresenter = QueryPresenter()
                    mQueryPresenter!!.attachView(mQueryCallback)
                    mQueryPresenter!!.fetchQueryResult(postQueryEntity)
                }
            })
        }
    }

    override fun onLoadError(t: Throwable) {
        Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
    }

    private val mQueryCallback: QueryCallback = object : QueryCallback {
        override fun onLoadQueryData(backResultData: BackResultData<List<QueryResultEntity>>) {
            if (backResultData.isState) {
                val queryResultEntityList = backResultData.data!!
                val reportParcelable = ReportParcelable(queryResultEntityList[0].drugNameEntity!!.drugOne,
                        queryResultEntityList[0].drugNameEntity!!.drugTwo,
                        queryResultEntityList[0].result, queryResultEntityList[1].result,
                        queryResultEntityList[0].score, queryResultEntityList[1].score)
                val intent = Intent(activity, GaugingReportActivity::class.java)
                intent.putExtra("queryData", reportParcelable)
                startActivity(intent)
                requireActivity().overridePendingTransition(R.anim.translate_right_in, R.anim.translate_left_out)
            } else {
                Toast.makeText(activity, backResultData.msg, Toast.LENGTH_LONG).show()
            }
        }

        override fun showErrorMsg(msg: String?) {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
        if (mRecordPresenter != null) {
            mRecordPresenter!!.detachView()
        }
        if (mQueryPresenter != null) {
            mQueryPresenter!!.detachView()
        }
    }

    override fun showErrorMsg(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}