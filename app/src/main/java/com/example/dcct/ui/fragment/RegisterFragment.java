package com.example.dcct.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcct.base.BaseFragment;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.RegisterUserEntity;
import com.example.dcct.databinding.FragmentRegisterBinding;
import com.example.dcct.model.Impl.RegisterModelImp;
import com.example.dcct.model.RegisterModel;
import com.example.dcct.presenter.RegisterPresenter;
import com.example.dcct.utils.PasswordMatchingUtils;
import com.example.dcct.R;
import com.example.dcct.utils.SnackBarUtil;
import com.example.dcct.view.RegisterCallback;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFragment extends BaseFragment implements RegisterCallback {

    private String password;
    private String username;
    private String email;
    private RegisterPresenter mRegisterPresenter;
    private isRegisterListener mIsRegisterListener;
    private FragmentRegisterBinding mBinding;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentRegisterBinding.inflate( getLayoutInflater() );
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        //检查输入邮箱格式
        checkEmailFormat( mBinding.textInputLayoutEmail.getEditText(), mBinding.textInputLayoutEmail);
        //检测两个输入框中输入的内容是否一致（情景：当第一次密码输入完成）
        if (mBinding.textInputLayoutPwdr.getEditText() != null) {
            mBinding.textInputLayoutPwdr.getEditText().addTextChangedListener( new
                    PasswordMatchingUtils( mBinding.textInputLayoutPwdr, mBinding.textInputLayoutPwdAgain, 0 ) );
        }
        //同样需要再次检测两个输入框中的内容是否相同（情景：第二次输入密码，并点击注册按钮）
        if (mBinding.textInputLayoutPwdAgain.getEditText() != null) {
            mBinding.textInputLayoutPwdAgain.getEditText().addTextChangedListener(
                    new PasswordMatchingUtils( mBinding.textInputLayoutPwdr, mBinding.textInputLayoutPwdAgain, 1 ) );
            mBinding.ivRegister.setOnClickListener( v -> {
                username = Objects.requireNonNull( mBinding.textInputLayoutUserName.getEditText() ).getText().toString();
                email = Objects.requireNonNull( mBinding.textInputLayoutEmail.getEditText() ).getText().toString();
                password = Objects.requireNonNull( mBinding.textInputLayoutPwdr.getEditText() ).getText().toString();
                if (username.equals( "" ) || email.equals( "" ) || password.equals( "" )) {
                    SnackBarUtil.ShortSnackbar( mBinding.snackBarHint, "信息输入不完整！！！", SnackBarUtil.Warning ).show();
                } else {
                    //向服务器提交数据
                    RegisterUserEntity registerUserEntity = new RegisterUserEntity( username, email, MD5Decode32( password ) );
                    mRegisterPresenter = new RegisterPresenter();
                    mRegisterPresenter.attachView( this );
                    mRegisterPresenter.fetchRegisterResult( registerUserEntity );
                    //在onLoadRegisterData方法中接受服务器返回的数据
                }
            } );
        }
    }

    @Override
    public void onLoadRegisterData(BackResultData backData) {
        if (backData.isState()) {
            SnackBarUtil.ShortSnackbar( mBinding.snackBarHint, "恭喜你，注册成功！！！", SnackBarUtil.Confirm ).show();
            mIsRegisterListener.setRegisterToLogin( true );
        } else {
            SnackBarUtil.ShortSnackbar( mBinding.snackBarHint, backData.getMsg(), SnackBarUtil.Alert ).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRegisterPresenter != null) {
            mRegisterPresenter.detachView();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof RegisterFragment.isRegisterListener) {
            mIsRegisterListener = (RegisterFragment.isRegisterListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement isRegisterListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mIsRegisterListener = null;
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText( getActivity(), msg, Toast.LENGTH_SHORT ).show();
    }

    public interface isRegisterListener {
        void setRegisterToLogin(boolean state);
    }
}
