package com.example.dcct.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.dcct.R
import com.example.dcct.base.BaseActivity
import com.example.dcct.databinding.ActivityGuideBinding
import java.util.*

class GuideActivity : BaseActivity(), OnPageChangeListener, View.OnClickListener {
    private var mCurrentItem = 0
    private val guideList: MutableList<Int> = ArrayList()
    private var mBinding: ActivityGuideBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGuideBinding.inflate(LayoutInflater.from(this))
        setImmersive()
        setContentView(mBinding!!.root)
        mBinding!!.comeIndex.setOnClickListener(this)
        data
        addGuidePicture()
        mBinding!!.indicate.getChildAt(0).isEnabled = true
        mBinding!!.viewPager.addOnPageChangeListener(this)
        mBinding!!.viewPager.adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return guideList.size
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val iv = ImageView(container.context)
                iv.scaleType = ImageView.ScaleType.FIT_XY
                iv.setImageResource(guideList[position])
                container.addView(iv)
                return iv
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View)
            }
        }
    }

    private fun addGuidePicture() {
        guideList.add(R.drawable.guide1)
        guideList.add(R.drawable.guide2)
        guideList.add(R.drawable.guide3)
    }

    //创建底部指示器(小圆点)
    //设置宽高
    private val data: Unit
        //设置间隔
        //添加到LinearLayout
        get() {
            var view: View
            for (i in 0..2) {
                //创建底部指示器(小圆点)
                view = View(this)
                view.setBackgroundResource(R.drawable.indicator)
                view.isEnabled = false
                //设置宽高
                val layoutParams = LinearLayout.LayoutParams(20, 20)
                //设置间隔
                if (i != 0) {
                    layoutParams.leftMargin = 30
                }
                //添加到LinearLayout
                mBinding!!.indicate.addView(view, layoutParams)
            }
        }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageSelected(position: Int) {
        //当滑动至最后一页时，隐藏指示器，显示按钮
        if (position == 2) {
            mBinding!!.indicate.visibility = View.GONE
            mBinding!!.comeIndex.visibility = View.VISIBLE
        } else {
            mBinding!!.indicate.visibility = View.VISIBLE
            mBinding!!.comeIndex.visibility = View.GONE
        }
        //指示器变化效果
        mBinding!!.indicate.getChildAt(mCurrentItem).isEnabled = false
        mBinding!!.indicate.getChildAt(position).isEnabled = true
        mCurrentItem = position
    }

    override fun onPageScrollStateChanged(state: Int) {}
    override fun onClick(v: View) {
        if (isLoginSuccess) {
            activityJump(this@GuideActivity, MainActivity::class.java)
        } else {
            activityJump(this@GuideActivity, LoginAndRegisterActivity::class.java)
        }
        translatingAnimation_rightToLeft()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}