package com.example.dcct.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.dcct.R;
import com.example.dcct.databinding.ActivityGaugingReportBinding;
import com.example.dcct.model.internet.ReportParcelable;

public class GaugingReportActivity extends AppCompatActivity {

    private ActivityGaugingReportBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityGaugingReportBinding.inflate( LayoutInflater.from( this ) );
        setContentView(mBinding.getRoot());

        mBinding.toolbar.setTitle("");//必须加上
        setSupportActionBar( mBinding.toolbar);
        mBinding.toolbar.setNavigationOnClickListener( v -> onBackPressed() );

        getGaugingReport();
    }

    private void getGaugingReport() {
        ReportParcelable reportParcelable = getIntent().getParcelableExtra( "queryData"  );
        if (reportParcelable != null) {
            mBinding.drug1.setText( reportParcelable.getDrugOne1() );
            mBinding.drug2.setText( reportParcelable.getDrugTwo1() );
            mBinding.firstDrug1.setText( reportParcelable.getDrugOne1() );
            mBinding.firstDrug2.setText( reportParcelable.getDrugTwo1() );
            mBinding.secondDrug1.setText( reportParcelable.getDrugTwo1() );
            mBinding.secondDrug2.setText( reportParcelable.getDrugOne1() );
            mBinding.firstResult.setText( reportParcelable.getResult1() );
            mBinding.secondResult.setText( reportParcelable.getResult2() );
            mBinding.firstScore.setText( String.valueOf( reportParcelable.getScore1() ) );
            mBinding.secondScore.setText( String.valueOf( reportParcelable.getScore2() ) );
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_left_in, R.anim.translate_right_out);
    }
}
