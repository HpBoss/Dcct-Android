package com.example.dcct.ui.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dcct.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MAndFoodFragment extends Fragment {

    private EditText mEditText3;
    private EditText mEditText4;

    public MAndFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_food, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mEditText3 = getView().findViewById(R.id.editText3);
        mEditText4 = getView().findViewById(R.id.editText4);
    }

    public boolean doCheckNotEmpty() {
        return !mEditText3.getText().toString().equals("") || !mEditText4.getText().toString().equals("");
    }

    public boolean doCheckCompleteAll(){
        return !mEditText3.getText().toString().equals("") && !mEditText4.getText().toString().equals("");
    }

    public List<String> backTextData(){
        List<String> list = new ArrayList<>( );
        list.add( mEditText3.getText().toString() );
        list.add( mEditText4.getText().toString() );
        return list;
    }

    public void clearEditeContent(){
        mEditText3.setText( "" );
        mEditText4.setText( "" );
    }
}
