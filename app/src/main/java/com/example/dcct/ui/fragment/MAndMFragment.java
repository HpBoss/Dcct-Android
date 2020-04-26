package com.example.dcct.ui.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dcct.R;
import com.example.dcct.databinding.FragmentMMBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static androidx.navigation.ui.NavigationUI.onNavDestinationSelected;


/**
 * A simple {@link Fragment} subclass.
 */
public class MAndMFragment extends Fragment {


    private FragmentMMBinding mBinding;

    public MAndMFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMMBinding.inflate( getLayoutInflater() );
        return mBinding.getRoot();
    }

    public boolean doCheckNotEmpty() {
        return !mBinding.editText1.getText().toString().equals("") || !mBinding.editText2.getText().toString().equals("");
    }

    public boolean doCheckCompleteAll(){
        return !mBinding.editText1.getText().toString().equals("") && !mBinding.editText2.getText().toString().equals("");
    }

    public List<String> backTextData(){
        List<String> list = new ArrayList<>( );
        list.add( mBinding.editText1.getText().toString() );
        list.add( mBinding.editText2.getText().toString() );
        return list;
    }

    public void clearEditContent(){
        mBinding.editText1.setText( "" );
        mBinding.editText2.setText( "" );
    }
}
