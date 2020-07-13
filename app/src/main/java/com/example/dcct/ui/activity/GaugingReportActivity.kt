package com.example.dcct.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.dcct.R
import com.example.dcct.base.BaseActivity
import com.example.dcct.bean.ReportParcelable
import com.example.dcct.databinding.ActivityGaugingReportBinding

class GaugingReportActivity : BaseActivity() {
    private var mBinding: ActivityGaugingReportBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGaugingReportBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding!!.root)
        mBinding!!.toolbar.title = "" //必须加上
        setSupportActionBar(mBinding!!.toolbar)
        mBinding!!.toolbar.setNavigationOnClickListener { v: View? -> onBackPressed() }
        gaugingReport
    }

    private val gaugingReport: Unit
        get() {
            val reportParcelable: ReportParcelable = intent.getParcelableExtra("queryData")
            mBinding!!.drug1.text = reportParcelable.drugOne1
            mBinding!!.drug2.text = reportParcelable.drugTwo1
            mBinding!!.firstDrug1.text = reportParcelable.drugOne1
            mBinding!!.firstDrug2.text = reportParcelable.drugTwo1
            mBinding!!.secondDrug1.text = reportParcelable.drugTwo1
            mBinding!!.secondDrug2.text = reportParcelable.drugOne1
            mBinding!!.firstResult.text = reportParcelable.result1
            mBinding!!.secondResult.text = reportParcelable.result2
            mBinding!!.firstScore.text = reportParcelable.score1.toString()
            mBinding!!.secondScore.text = reportParcelable.score2.toString()
        }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.translate_left_in, R.anim.translate_right_out)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}