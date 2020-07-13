package com.example.dcct.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.CoverEntity
import com.example.dcct.databinding.FragmentGroundBinding
import com.example.dcct.presenter.CoverPresenter
import com.example.dcct.ui.adapter.CardInformationAdapter
import com.example.dcct.view.ImageUrlCallback
import java.util.*

class GroundFragment : Fragment(), ImageUrlCallback {
    private val cardInformationList: MutableList<CoverEntity> = ArrayList()
    private var mCoverPresenter: CoverPresenter? = null
    private var mBinding: FragmentGroundBinding? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentGroundBinding.inflate(layoutInflater)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //竖直流布局
        val linearLayoutManager = LinearLayoutManager(activity)
        mBinding!!.recycleview.layoutManager = linearLayoutManager
        requestCardInformation()
        //给每一个item添加分割线
        /*recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),DividerItemDecoration.VERTICAL));*/
    }

    /**
     * 请求图片URL
     */
    private fun requestCardInformation() {
        mCoverPresenter = CoverPresenter()
        mCoverPresenter!!.attachView(this)
        mCoverPresenter!!.fetchImageUrl()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
        if (mCoverPresenter != null) {
            mCoverPresenter!!.detachView()
        }
    }

    override fun onLoadImageUrl(coverEntities: List<CoverEntity>) {
        cardInformationList.addAll(coverEntities)
        val cardInformationAdapter = CardInformationAdapter(cardInformationList, requireActivity())
        mBinding!!.recycleview.adapter = cardInformationAdapter
    }

    override fun showErrorMsg(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}