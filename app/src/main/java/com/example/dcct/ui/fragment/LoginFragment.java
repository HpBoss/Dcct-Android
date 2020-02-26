package com.example.dcct.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcct.R;
import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.LoginUserEntity;
import com.example.dcct.model.internet.model.UserEntity;
import com.example.dcct.presenter.Imp.LoginPresenterImp;
import com.example.dcct.presenter.LoginPresenter;
import com.example.dcct.view.LoginCallback;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginFragment extends Fragment implements LoginCallback {

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPwd;
    private String email;
    private String password;
    private ImageView login;
    private TextView contentView;
    private InformationDetermine mInformationDetermine;
    private LoginPresenter mLoginPresenter;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        textInputLayoutPwd = view.findViewById(R.id.textInputLayoutPwd);
        textInputLayoutEmail = view.findViewById(R.id.textInputLayoutEmail);
        login = view.findViewById(R.id.iv_login);
        contentView = view.findViewById( R.id.snackBarLogin );

        if (textInputLayoutEmail.getEditText() != null) {
            textInputLayoutEmail.getEditText().setOnFocusChangeListener( (v, hasFocus) -> {
                if (!hasFocus) {
                    if (!formatDecision( textInputLayoutEmail.getEditText().getText().toString() )){
                        textInputLayoutEmail.setError( "邮箱格式错误" );
                    }else {
                        textInputLayoutEmail.setErrorEnabled( false );
                    }
                }
            } );
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        login.setOnClickListener( v -> {
            if (textInputLayoutEmail.getEditText() != null && textInputLayoutPwd.getEditText() != null) {
                email = textInputLayoutEmail.getEditText().getText().toString();
                password = textInputLayoutPwd.getEditText().getText().toString();

                if (email.contentEquals( "" ) && !password.contentEquals( "" )){
                    Toast.makeText( getActivity(),"邮箱号为空" ,Toast.LENGTH_SHORT).show();
                }else if (!email.contentEquals( "" ) && password.contentEquals( "" )){
                    Toast.makeText( getActivity(),"密码为空" ,Toast.LENGTH_SHORT).show();
                }else if (email.contentEquals( "" ) && password.contentEquals( "" )){
                    Toast.makeText( getActivity(),"邮箱、密码均为空！" ,Toast.LENGTH_SHORT).show();
                }else{
                    //向服务器提交数据
                    mLoginPresenter = new LoginPresenterImp();
                    mLoginPresenter.registerCallBack( this );
                    LoginUserEntity loginUserEntity = new LoginUserEntity(email,password);
                    mLoginPresenter.postLoginData( loginUserEntity );
                    //在onLoadLoginData方法中接受数据
                }

            }
        } );
    }

    @Override
    public void onLoadLoginData(BackResultData<UserEntity> backData) {
        if (backData.isState()) {
            if (getActivity() != null) {
                Toast.makeText( getActivity(),backData.getMsg(),Toast.LENGTH_SHORT ).show();
                //将登录成功的信息使用SharedPreferences存储
                SharedPreferences preferences = getActivity().getSharedPreferences( "SHARE_APP_LOGIN", Context.MODE_PRIVATE );
                preferences.edit().putBoolean("LOGIN_SUCCESS", true).apply();
            }
            UserEntity dataBean = backData.getData();
////            UserDatabase userDatabase = UserDatabase.getInstance( getActivity() );
////            UserDao userDao = userDatabase.getUserDao();
////            UserEntity userEntity = new UserEntity(dataBean.getUid(),dataBean.getNickname(),dataBean.getEmail());
////            /*userDao.insertUser( userEntity );*/
////            new InsertAsyncTask(userDao).execute(userEntity);
            SharedPreferences preferences = getActivity().getSharedPreferences( "SHARE_APP_DATA",Context.MODE_PRIVATE );
            preferences.edit().putString( "nickname", dataBean.getNickname())
            .putLong( "uid",dataBean.getUid() )
            .apply();
            //向父活动发送登录成功的通知
            mInformationDetermine.loginSuccess();
        }else {
            Toast.makeText( getActivity(),backData.getMsg(),Toast.LENGTH_SHORT ).show();
//            SnackBarUtil.ShortSnackbar(contentView,backData.getMsg(), SnackBarUtil.Alert).show();
        }
    }
//    static class InsertAsyncTask extends AsyncTask<UserEntity,Void,Void>{
//        private UserDao mUserDao;
//
//        public InsertAsyncTask(UserDao userDao) {
//            mUserDao = userDao;
//        }
//
//        @Override
//        protected Void doInBackground(UserEntity... userEntities) {
//            mUserDao.insertUser( userEntities );
//            return null;
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLoginPresenter != null) {
            mLoginPresenter.unregisterCallBack( this );
        }
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
        super.onAttach(context);
        if (context instanceof InformationDetermine) {
            mInformationDetermine = (InformationDetermine) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InformationDetermine");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mInformationDetermine = null;
    }

    private boolean formatDecision(String email) {
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
