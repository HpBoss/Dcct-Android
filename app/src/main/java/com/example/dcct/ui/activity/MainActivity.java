package com.example.dcct.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dcct.databinding.ActivityMainBinding;
import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.presenter.Imp.SignOutPresenterImp;
import com.example.dcct.presenter.SignOutPresenter;
import com.example.dcct.ui.fragment.MAndFoodFragment;
import com.example.dcct.ui.fragment.MAndMFragment;
import com.example.dcct.R;
import com.example.dcct.base.BaseActivity;
import com.example.dcct.ui.fragment.GaugingFragment;
import com.example.dcct.view.SignOutCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import java.lang.ref.WeakReference;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends BaseActivity implements GaugingFragment.transmitFragment, SignOutCallback {

    private NavController mNavController;
    private MAndMFragment mMAndMFragment;
    private MAndFoodFragment mMAndFoodFragment;
    private int oldId;
    private int secondId;
    private static String[] titleArray = new String[]{"广场","消息"};
    private SignOutPresenter mSignOutPresenter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate( LayoutInflater.from( this ) );
        setContentView(mBinding.getRoot());
        addStatusViewWithColor(this,getResources().getColor(R.color.colorPrimary));
        initView();

    }

    /**
     * 保存当activity销毁时点击的BottomNavigationView按钮的itemId，
     * 以至于返回该活动时还是之前的界面
//     * @param outState
     */
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("LastSelectItemId",mNavView.getSelectedItemId());
//        Log.d("selectedId",String.valueOf(mNavView.getSelectedItemId()));
//    }

//    static class InsertAsyncTask extends AsyncTask<UserEntity,Void,Void> {
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
    private void initView() {
//        UserDatabase userDatabase = UserDatabase.getInstance( this );
//        UserDao userDao = userDatabase.getUserDao();
//        mTitleName.setText( userDao.getNickName() );
        SharedPreferences preferences = getSharedPreferences( "SHARE_APP_DATA", Context.MODE_PRIVATE );
        mBinding.titleUsername.setText( preferences.getString( "nickname","XX" ) );
        mBinding.titleUsername.setOnClickListener( v -> {
            displayAlertDialog();
        } );
        /*从服务器获取用户名使用mTitleName显示出来*/
        mBinding.mainToolbar.setTitle("");
        setSupportActionBar(mBinding.mainToolbar);

        oldId = mBinding.navView.getMenu().getItem(0).getItemId();
        secondId = mBinding.navView.getMenu().getItem(1).getItemId();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setupWithNavControllers();
    }

    @Override
    public void setTwoFragment(MAndMFragment fragment1,MAndFoodFragment fragment2) {
        mMAndMFragment = fragment1;
        mMAndFoodFragment = fragment2;
    }


    public void setupWithNavControllers() {
        mBinding.navView.setOnNavigationItemSelectedListener(
                item -> {
                    int itemId = item.getItemId();
                    switch (itemId){
                        case R.id.navigation_ground:
                            selectOneThirdId(itemId,titleArray[0]);
                            break;
                        case R.id.navigation_gauging:
                            mNavController.navigate(itemId);
                            clickGaugingChangeTitle();
                            break;
                        case R.id.navigation_record:
                            selectOneThirdId(itemId,titleArray[1]);
                            break;
                    }
                    oldId = itemId;
                    return true;
                } );
        final WeakReference<BottomNavigationView> weakReference =
                new WeakReference<>(mBinding.navView);
        mNavController.addOnDestinationChangedListener(
                new NavController.OnDestinationChangedListener() {
                    @Override
                    public void onDestinationChanged(@NonNull NavController controller,
                                                     @NonNull NavDestination destination, @Nullable Bundle arguments) {
                        BottomNavigationView view = weakReference.get();
                        if (view == null) {
                            mNavController.removeOnDestinationChangedListener(this);
                            return;
                        }
                        Menu menu = view.getMenu();
                        for (int h = 0, size = menu.size(); h < size; h++) {
                            MenuItem item = menu.getItem(h);
                            if (matchDestination(destination, item.getItemId())) {
                                item.setChecked(true);
                            }
                        }
                    }
                });
    }
    static boolean matchDestination(@NonNull NavDestination destination,
                                    @IdRes int destId) {
        NavDestination currentDestination = destination;
        while (currentDestination.getId() != destId && currentDestination.getParent() != null) {
            currentDestination = currentDestination.getParent();
        }
        return currentDestination.getId() == destId;
    }

    public void selectOneThirdId(int itemId,String title){
        //对于检测页面输入信息，未提交的，给予dialog提示
//        if (oldId == secondId){
//            if (mMAndMFragment !=null && mMAndFoodFragment != null){
//                if (mMAndMFragment.doCheckNotEmpty() || mMAndFoodFragment.doCheckNotEmpty()) {
//                    displayInputDialog(itemId);
//                }else {
//                    mNavController.navigate(itemId);
//                    clickChangeTitle(title);
//                }
//            }else {
//                mNavController.navigate(itemId);
//                clickChangeTitle(title);
//            }
//        }else {
//            mNavController.navigate(itemId);
//            clickChangeTitle(title);
//        }
        mNavController.navigate(itemId);
        clickChangeTitle(title);
    }
//    public void displayInputDialog(final int itemId){
//        new AlertDialog.Builder(this)
//        .setMessage("是否要放弃本次检测？")
//        .setCancelable(false)
//        .setPositiveButton("Yes", (dialogInterface, i) -> {
//            mNavController.navigate(itemId);
//            switch (itemId){
//                case R.id.navigation_ground:
//                    clickChangeTitle(titleArray[0]);
//                    break;
//                case R.id.navigation_record:
//                    clickChangeTitle(titleArray[1]);
//                    break;
//            }
//        } )
//        .setNegativeButton("No", (dialog, which) -> {
//
//        } )
//        .show();
//
//    }

    public void displayAlertDialog(){
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("确定要退出登录?")
                .setConfirmText("退出登录!")
                .setConfirmClickListener( sDialog -> {
                    sDialog.dismissWithAnimation();

                    SharedPreferences preferences = getSharedPreferences( "SHARE_APP_DATA",Context.MODE_PRIVATE );
                    long uid = preferences.getLong( "uid", 1 );
                    mSignOutPresenter = new SignOutPresenterImp();
                    mSignOutPresenter.registerCallBack( this );
                    mSignOutPresenter.postSignOutId( uid );

                } )
                .showCancelButton(true)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSignOutPresenter != null){
            mSignOutPresenter.unregisterCallBack( this );
        }
    }

    private void clickGaugingChangeTitle() {
        mBinding.titleFragment.setVisibility(View.GONE);
        mBinding.tabSegment.setVisibility(View.VISIBLE);
    }

    public void clickChangeTitle(String title){
        mBinding.titleFragment.setVisibility(View.VISIBLE);
        mBinding.titleFragment.setText(title);
        mBinding.tabSegment.setVisibility(View.GONE);
    }

    @Override
    public void onLoadSignOutSuccess(BackResultData backResultData) {
        if (backResultData.isState()) {
            SharedPreferences preferences = getSharedPreferences( "SHARE_APP_LOGIN", Context.MODE_PRIVATE );
            preferences.edit().putBoolean("LOGIN_SUCCESS", false).apply();

            Toast.makeText( MainActivity.this,backResultData.getMsg(),Toast.LENGTH_SHORT ).show();
            activityJump(this,LoginAndRegisterActivity.class);
            translatingAnimation_leftToRight();
        }
    }
}
