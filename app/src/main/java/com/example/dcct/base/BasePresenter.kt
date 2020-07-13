package com.example.dcct.base

import java.lang.ref.WeakReference

open class BasePresenter<T> {
    @JvmField
    protected var mCallBackView: WeakReference<T>? = null
    fun attachView(view: T) {
        mCallBackView = WeakReference(view) //弱引用
    }

    fun detachView() {
        if (mCallBackView != null) {
            mCallBackView!!.clear()
            mCallBackView = null
        }
    }
}