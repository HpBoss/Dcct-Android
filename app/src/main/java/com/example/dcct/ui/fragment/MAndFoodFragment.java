package com.example.dcct.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dcct.R;
import com.example.dcct.databinding.FragmentMFoodBinding;
import com.example.dcct.databinding.FragmentMMBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MAndFoodFragment extends Fragment {

    private FragmentMFoodBinding mBinding;

    public MAndFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMFoodBinding.inflate( getLayoutInflater() );
        return mBinding.getRoot();
    }

    public boolean doCheckNotEmpty() {
        return !mBinding.editText3.getText().toString().equals("") || !mBinding.editText4.getText().toString().equals("");
    }

    public boolean doCheckCompleteAll(){
        return !mBinding.editText3.getText().toString().equals("") && !mBinding.editText4.getText().toString().equals("");
    }

    public List<String> backTextData(){
        List<String> list = new ArrayList<>( );
        list.add( mBinding.editText3.getText().toString() );
        list.add( mBinding.editText4.getText().toString() );
        return list;
    }

    public void clearEditContent(){
        mBinding.editText3.setText( "" );
        mBinding.editText4.setText( "" );
    }
}
