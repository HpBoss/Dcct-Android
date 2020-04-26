package com.example.dcct.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcct.R;
import com.example.dcct.base.BaseFragment;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.LoginUserEntity;
import com.example.dcct.bean.TokenEntity;
import com.example.dcct.bean.UserEntity;
import com.example.dcct.databinding.FragmentLoginBinding;
import com.example.dcct.model.Impl.LoginModelImp;
import com.example.dcct.model.LoginModel;
import com.example.dcct.presenter.LoginPresenter;
import com.example.dcct.view.LoginCallback;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LoginFragment extends BaseFragment implements LoginCallback {

    private String email;
    private String password;
    private InformationDetermine mInformationDetermine;
    private LoginPresenter mLoginPresenter;
    private FragmentLoginBinding mBinding;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentLoginBinding.inflate( getLayoutInflater() );
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        //检查输入邮箱格式
        checkEmailFormat( mBinding.textInputLayoutEmail.getEditText(),mBinding.textInputLayoutEmail );
        //点击登录按钮
        mBinding.ivLogin.setOnClickListener( v -> {
            if (mBinding.textInputLayoutEmail.getEditText() != null && mBinding.textInputLayoutPwd.getEditText() != null) {
                email = mBinding.textInputLayoutEmail.getEditText().getText().toString();
                password = mBinding.textInputLayoutPwd.getEditText().getText().toString();

                if (email.contentEquals( "" ) && !password.contentEquals( "" )) {
                    Toast.makeText( getActivity(), "邮箱号为空", Toast.LENGTH_SHORT ).show();
                } else if (!email.contentEquals( "" ) && password.contentEquals( "" )) {
                    Toast.makeText( getActivity(), "密码为空", Toast.LENGTH_SHORT ).show();
                } else if (email.contentEquals( "" ) && password.contentEquals( "" )) {
                    Toast.makeText( getActivity(), "邮箱、密码均为空！", Toast.LENGTH_SHORT ).show();
                } else {
                    //向服务器提交数据,将password进行MD5算法加密
                    LoginUserEntity loginUserEntity = new LoginUserEntity( email, MD5Decode32( password ) );
                    mLoginPresenter = new LoginPresenter();
                    mLoginPresenter.attachView( this );
                    mLoginPresenter.fetchUserEntity( loginUserEntity );
                    //在onLoadLoginData方法中接受数据
                }
            }
        } );
    }

    @Override
    public void onLoadLoginData(BackResultData<TokenEntity> backData) {
        if (backData.isState()) {
            if (getActivity() != null) {
                Toast.makeText( getActivity(), backData.getMsg(), Toast.LENGTH_SHORT ).show();
                //将登录成功的信息使用SharedPreferences存储
                SharedPreferences preferences = getActivity().getSharedPreferences( "SHARE_APP_LOGIN", Context.MODE_PRIVATE );
                preferences.edit().putBoolean( "LOGIN_SUCCESS", true ).apply();
            }
            TokenEntity dataBean = backData.getData();
            SharedPreferences preferences = getActivity().getSharedPreferences( "SHARE_APP_DATA", Context.MODE_PRIVATE );

//            Log.d( "uid",String.valueOf( dataBean.getUid() ) );
//            preferences.edit().putString( "nickname", dataBean.getNickname() )
//                    .putLong( "uid", dataBean.getUid() )
//                    .apply();
            //向父活动发送登录成功的通知
            mInformationDetermine.loginSuccess();
        } else {
            Toast.makeText( getActivity(), backData.getMsg(), Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLoginPresenter != null) {
            mLoginPresenter.detachView();
        }
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText( getActivity(), msg, Toast.LENGTH_SHORT ).show();
    }

    public interface InformationDetermine {
        void loginSuccess();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mInformationDetermine != null) {
            mInformationDetermine.loginSuccess();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof InformationDetermine) {
            mInformationDetermine = (InformationDetermine) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement InformationDetermine" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mInformationDetermine = null;
    }
}
