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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static androidx.navigation.ui.NavigationUI.onNavDestinationSelected;


/**
 * A simple {@link Fragment} subclass.
 */
public class MAndMFragment extends Fragment {

    private EditText mEditText1;
    private EditText mEditText2;

    public MAndMFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_m, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mEditText1 = Objects.requireNonNull(getView()).findViewById(R.id.editText1);
        mEditText2 = getView().findViewById(R.id.editText2);
    }

    public boolean doCheckNotEmpty() {
        return !mEditText1.getText().toString().equals("") || !mEditText2.getText().toString().equals("");
    }

    public boolean doCheckCompleteAll(){
        return !mEditText1.getText().toString().equals("") && !mEditText2.getText().toString().equals("");
    }

    public List<String> backTextData(){
        List<String> list = new ArrayList<>( );
        list.add( mEditText1.getText().toString() );
        list.add( mEditText2.getText().toString() );
        return list;
    }

    public void clearEditeContent(){
        mEditText1.setText( "" );
        mEditText2.setText( "" );
    }
}
