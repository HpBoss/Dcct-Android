package com.example.dcct.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.RegisterUserEntity;
import com.example.dcct.presenter.Imp.RegisterPresenterImp;
import com.example.dcct.presenter.RegisterPresenter;
import com.example.dcct.utils.PasswordMatchingUtils;
import com.example.dcct.R;
import com.example.dcct.utils.SnackBarUtil;
import com.example.dcct.view.RegisterCallback;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment implements RegisterCallback {

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutUserName;
    private TextInputLayout textInputLayoutPwd;
    private TextInputLayout textInputLayoutPwdAgain;
    private String password;
    private String username;
    private String email;
    private ImageView register;
    private TextView contentView;
    private RegisterPresenter mRegisterPresenter;
    private isRegisterListener mIsRegisterListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        textInputLayoutPwd = view.findViewById(R.id.textInputLayoutPwdr);
        textInputLayoutEmail = view.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutUserName = view.findViewById(R.id.textInputLayoutUserName);
        textInputLayoutPwdAgain = view.findViewById(R.id.textInputLayoutPwdAgain);
        if (textInputLayoutPwd.getEditText() != null){
            textInputLayoutPwd.getEditText().addTextChangedListener(new
                    PasswordMatchingUtils(textInputLayoutPwd,textInputLayoutPwdAgain,0));
        }
        register = view.findViewById(R.id.iv_register);
        contentView = view.findViewById(R.id.snackBarHint );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (textInputLayoutPwdAgain.getEditText() != null) {
            textInputLayoutPwdAgain.getEditText().addTextChangedListener(
                    new PasswordMatchingUtils(textInputLayoutPwd,textInputLayoutPwdAgain,1));
            register.setOnClickListener(v -> {
                username = textInputLayoutUserName.getEditText().getText().toString();
                email = textInputLayoutEmail.getEditText().getText().toString();
                password = textInputLayoutPwd.getEditText().getText().toString();
                if (username.equals("") || email.equals("") || password.equals("")) {
                    SnackBarUtil.ShortSnackbar(contentView, "信息输入不完整！！！", SnackBarUtil.Warning).show();
                } else {
                    //向服务器提交数据
                    mRegisterPresenter = new RegisterPresenterImp();
                    mRegisterPresenter.registerCallBack(this);
                    RegisterUserEntity registerUserEntity = new RegisterUserEntity(username, email, password);
                    mRegisterPresenter.postRegisterInfor( registerUserEntity );
                    //在onLoadRegisterData方法中接受服务器返回的数据
                }
            });
        }
    }

    @Override
    public void onLoadRegisterData(BackResultData backData) {
        if (backData.isState()) {
            SnackBarUtil.ShortSnackbar(contentView,"恭喜你，注册成功！！！",SnackBarUtil.Confirm).show();
            mIsRegisterListener.setRegisterToLogin(true);
        }else {
            SnackBarUtil.ShortSnackbar(contentView,backData.getMsg(), SnackBarUtil.Alert).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRegisterPresenter != null) {
            mRegisterPresenter.unregisterCallBack(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegisterFragment.isRegisterListener) {
            mIsRegisterListener = (RegisterFragment.isRegisterListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement isRegisterListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mIsRegisterListener = null;
    }

    public interface isRegisterListener{
         void setRegisterToLogin(boolean state);
    }
}
