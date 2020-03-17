package com.example.dcct.base;

import java.lang.ref.WeakReference;

public class BasePresenter<T> {
    protected WeakReference<T> mCallBackView;

    public void attachView(T view){
        mCallBackView = new WeakReference<>( view );//弱引用
    }

    public void detachView(){
        if (mCallBackView != null) {
            mCallBackView.clear();
            mCallBackView = null;
        }
    }
}
