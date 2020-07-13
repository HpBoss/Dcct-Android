package com.example.dcct.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.dcct.R
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.PostQueryEntity
import com.example.dcct.bean.QueryResultEntity
import com.example.dcct.bean.ReportParcelable
import com.example.dcct.databinding.FragmentGaugingBinding
import com.example.dcct.presenter.QueryPresenter
import com.example.dcct.ui.activity.GaugingReportActivity
import com.example.dcct.ui.adapter.GaugingPageAdapter
import com.example.dcct.view.QueryCallback
import com.google.android.material.tabs.TabLayout
import java.util.*

class GaugingFragment : Fragment(), QueryCallback {
    private var mMAndMFragment: MAndMFragment? = null
    private var mMAndFoodFragment: MAndFoodFragment? = null
    private var mTransmitFragment: transmitFragment? = null
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private var mQueryPresenter: QueryPresenter? = null
    private var mBinding: FragmentGaugingBinding? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentGaugingBinding.inflate(layoutInflater)
        addFragment()
        mTransmitFragment!!.setTwoFragment(mMAndMFragment, mMAndFoodFragment)
        return mBinding!!.root
    }

    private fun addFragment() {
        mMAndMFragment = MAndMFragment()
        mMAndFoodFragment = MAndFoodFragment()
        mFragmentList.add(mMAndMFragment!!)
        mFragmentList.add(mMAndFoodFragment!!)
        //添加过渡动画，测试
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabSegment: TabLayout = requireActivity().findViewById(R.id.tabSegment)
        tabSegment.setupWithViewPager(mBinding!!.viewPager)
        mBinding!!.viewPager.currentItem = 0
        val gaugingPageAdapter = GaugingPageAdapter(childFragmentManager,
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mFragmentList)
        mBinding!!.viewPager.adapter = gaugingPageAdapter
        mBinding!!.gaugingButton.setOnClickListener { v: View? ->
            val textData: List<String>
            val preferences = requireActivity()
                    .getSharedPreferences("SHARE_APP_DATA", Context.MODE_PRIVATE)
            val uid = preferences.getLong("uid", 1)
            mQueryPresenter = QueryPresenter()
            mQueryPresenter!!.attachView(this)
            if (mBinding!!.viewPager.currentItem == 0) {
                if (!mMAndMFragment!!.doCheckCompleteAll()) {
                    displayAlertDialog()
                } else {
                    textData = mMAndMFragment!!.backTextData()
                    val postQueryEntity = PostQueryEntity(textData[0], textData[1], uid)
                    mQueryPresenter!!.fetchQueryResult(postQueryEntity)
                    mMAndMFragment!!.clearEditContent()
                }
            } else {
                if (!mMAndFoodFragment!!.doCheckCompleteAll()) {
                    displayAlertDialog()
                } else {
                    textData = mMAndFoodFragment!!.backTextData()
                    val postQueryEntity = PostQueryEntity(textData[0], textData[1], uid)
                    mQueryPresenter!!.fetchQueryResult(postQueryEntity)
                    mMAndFoodFragment!!.clearEditContent()
                }
            }
        }
    }

    private fun jumpToReportActivity(queryResultEntityList: List<QueryResultEntity>) {
        val reportParcelable = ReportParcelable(queryResultEntityList[0].drugNameEntity!!.drugOne,
                queryResultEntityList[0].drugNameEntity!!.drugTwo,
                queryResultEntityList[0].result, queryResultEntityList[1].result,
                queryResultEntityList[0].score, queryResultEntityList[1].score)
        val intent = Intent(activity, GaugingReportActivity::class.java)
        intent.putExtra("queryData", reportParcelable)
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.translate_right_in, R.anim.translate_left_out)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
        if (mQueryPresenter != null) {
            mQueryPresenter!!.detachView()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mTransmitFragment = if (context is transmitFragment) {
            context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement transmitFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mTransmitFragment = null
    }

    override fun onLoadQueryData(backResultData: BackResultData<List<QueryResultEntity>>) {
        if (backResultData.isState) {
            val queryResultEntityList = backResultData.data
            Toast.makeText(activity, backResultData.msg, Toast.LENGTH_LONG).show()
            jumpToReportActivity(queryResultEntityList as List<QueryResultEntity>)
        } else {
            Toast.makeText(activity, backResultData.msg, Toast.LENGTH_LONG).show()
        }
    }

    override fun showErrorMsg(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    interface transmitFragment {
        fun setTwoFragment(fragment1: MAndMFragment?, fragment2: MAndFoodFragment?)
    }

    private fun displayAlertDialog() {
        SweetAlertDialog(requireActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("查询信息未填写完整")
                .setConfirmText("确定")
                .setConfirmClickListener { obj: SweetAlertDialog -> obj.dismissWithAnimation() }
                .show()
    }

    fun rotateAnimation() {
        val animation = AnimationUtils.loadAnimation(activity, R.anim.rotate_360_degress)
        /*LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);设置运行速度*/
        /*LinearInterpolator为匀速效果，AccelerateInterpolator为加速效果、DecelerateInterpolator为减速效果*/mBinding!!.gaugingButton.startAnimation(animation)
        /*mGaugingButton.clearAnimation();关闭动画*/
    }
}