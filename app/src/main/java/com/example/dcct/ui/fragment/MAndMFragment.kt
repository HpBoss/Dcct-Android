package com.example.dcct.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dcct.databinding.FragmentMMBinding
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MAndMFragment : Fragment() {
    private var mBinding: FragmentMMBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = FragmentMMBinding.inflate(layoutInflater)
        return mBinding!!.root
    }

    fun doCheckNotEmpty(): Boolean {
        return mBinding!!.editText1.text.toString() != "" || mBinding!!.editText2.text.toString() != ""
    }

    fun doCheckCompleteAll(): Boolean {
        return mBinding!!.editText1.text.toString() != "" && mBinding!!.editText2.text.toString() != ""
    }

    fun backTextData(): List<String> {
        val list: MutableList<String> = ArrayList()
        list.add(mBinding!!.editText1.text.toString())
        list.add(mBinding!!.editText2.text.toString())
        return list
    }

    fun clearEditContent() {
        mBinding!!.editText1.setText("")
        mBinding!!.editText2.setText("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}