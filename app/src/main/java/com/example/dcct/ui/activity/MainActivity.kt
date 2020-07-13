package com.example.dcct.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.dcct.R
import com.example.dcct.base.BaseActivity
import com.example.dcct.bean.BackResultData
import com.example.dcct.databinding.ActivityMainBinding
import com.example.dcct.presenter.SignOutPresenter
import com.example.dcct.ui.activity.MainActivity
import com.example.dcct.ui.fragment.GaugingFragment.transmitFragment
import com.example.dcct.ui.fragment.MAndFoodFragment
import com.example.dcct.ui.fragment.MAndMFragment
import com.example.dcct.view.SignOutCallback
import java.lang.ref.WeakReference

class MainActivity : BaseActivity(), transmitFragment, SignOutCallback {
    private var mNavController: NavController? = null
    private var oldId = 0
    private var mSignOutPresenter: SignOutPresenter? = null
    private var mBinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        initView()
    }

    private fun initView() {
        val preferences = getSharedPreferences("SHARE_APP_DATA", Context.MODE_PRIVATE)
        mBinding!!.titleUsername.text = preferences.getString("nickname", "XX")
        mBinding!!.titleUsername.setOnClickListener { displayAlertDialog() }
        /*从服务器获取用户名使用mTitleName显示出来*/mBinding!!.mainToolbar.title = ""
        setSupportActionBar(mBinding!!.mainToolbar)
        oldId = mBinding!!.navView.menu.getItem(0).itemId
        val secondId = mBinding!!.navView.menu.getItem(1).itemId

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupWithNavControllers()
    }

    override fun setTwoFragment(fragment1: MAndMFragment?, fragment2: MAndFoodFragment?) {}
    private fun setupWithNavControllers() {
        mBinding!!.navView.setOnNavigationItemSelectedListener { item: MenuItem ->
            val itemId = item.itemId
            when (itemId) {
                R.id.navigation_ground -> selectOneThirdId(itemId, titleArray[0])
                R.id.navigation_gauging -> {
                    mNavController!!.navigate(itemId)
                    clickGaugingChangeTitle()
                }
                R.id.navigation_record -> selectOneThirdId(itemId, titleArray[1])
            }
            oldId = itemId
            true
        }
        val weakReference = WeakReference(mBinding!!.navView)
        mNavController!!.addOnDestinationChangedListener(
                object : OnDestinationChangedListener {
                    override fun onDestinationChanged(controller: NavController,
                                                      destination: NavDestination, arguments: Bundle?) {
                        val view = weakReference.get()
                        if (view == null) {
                            mNavController!!.removeOnDestinationChangedListener(this)
                            return
                        }
                        val menu = view.menu
                        var h = 0
                        val size = menu.size()
                        while (h < size) {
                            val item = menu.getItem(h)
                            if (matchDestination(destination, item.itemId)) {
                                item.isChecked = true
                            }
                            h++
                        }
                    }
                })
    }

    private fun selectOneThirdId(itemId: Int, title: String?) {
        mNavController!!.navigate(itemId)
        clickChangeTitle(title)
    }

    private fun displayAlertDialog() {
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("确定要退出登录?")
                .setConfirmText("退出登录!")
                .setConfirmClickListener { sDialog: SweetAlertDialog ->
                    sDialog.dismissWithAnimation()
                    val preferences = getSharedPreferences("SHARE_APP_DATA", Context.MODE_PRIVATE)
                    val uid = preferences.getLong("uid", 1)
                    mSignOutPresenter = SignOutPresenter()
                    mSignOutPresenter!!.attachView(this)
                    mSignOutPresenter!!.fetchSignState(uid)
                }
                .showCancelButton(true)
                .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mSignOutPresenter!!.detachView()
        mBinding = null
    }

    private fun clickGaugingChangeTitle() {
        mBinding!!.titleFragment.visibility = View.GONE
        mBinding!!.tabSegment.visibility = View.VISIBLE
    }

    private fun clickChangeTitle(title: String?) {
        mBinding!!.titleFragment.visibility = View.VISIBLE
        mBinding!!.titleFragment.text = title
        mBinding!!.tabSegment.visibility = View.GONE
    }

    override fun onLoadSignOutSuccess(backResultData: BackResultData<*>) {
        if (backResultData.isState) {
            val preferences = getSharedPreferences("SHARE_APP_LOGIN", Context.MODE_PRIVATE)
            preferences.edit().putBoolean("LOGIN_SUCCESS", false).apply()
            Toast.makeText(this@MainActivity, backResultData.msg, Toast.LENGTH_SHORT).show()
            activityJump(this, LoginAndRegisterActivity::class.java)
            translatingAnimation_leftToRight()
        }
    }

    override fun showErrorMsg(msg: String?) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val titleArray = arrayOf("广场", "消息")
        fun matchDestination(destination: NavDestination,
                             @IdRes destId: Int): Boolean {
            var currentDestination: NavDestination? = destination
            while (currentDestination!!.id != destId && currentDestination.parent != null) {
                currentDestination = currentDestination.parent
            }
            return currentDestination.id == destId
        }
    }
}