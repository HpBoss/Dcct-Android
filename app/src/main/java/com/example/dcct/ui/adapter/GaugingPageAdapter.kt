package com.example.dcct.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class GaugingPageAdapter(fm: FragmentManager, behavior: Int, private val fragmentList: List<Fragment>) : FragmentStatePagerAdapter(fm, behavior) {
    private val titleGauging = arrayOf("药+药", "药+食")
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleGauging[position]
    }

}