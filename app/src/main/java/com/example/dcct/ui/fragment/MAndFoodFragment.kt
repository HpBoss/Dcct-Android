package com.example.dcct.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dcct.databinding.FragmentMFoodBinding
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MAndFoodFragment : Fragment() {
    private var mBinding: FragmentMFoodBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentMFoodBinding.inflate(layoutInflater)
        return mBinding!!.root
    }

    fun doCheckNotEmpty(): Boolean {
        return mBinding!!.editText3.text.toString() != "" || mBinding!!.editText4.text.toString() != ""
    }

    fun doCheckCompleteAll(): Boolean {
        return mBinding!!.editText3.text.toString() != "" && mBinding!!.editText4.text.toString() != ""
    }

    fun backTextData(): List<String> {
        val list: MutableList<String> = ArrayList()
        list.add(mBinding!!.editText3.text.toString())
        list.add(mBinding!!.editText4.text.toString())
        return list
    }

    fun clearEditContent() {
        mBinding!!.editText3.setText("")
        mBinding!!.editText4.setText("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}