package com.example.dcct.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.dcct.R;
import com.example.dcct.databinding.ActivityGaugingReportBinding;
import com.example.dcct.model.internet.model.ReportSerializable;

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
        ReportSerializable reportSerializable = (ReportSerializable) getIntent().getSerializableExtra( "queryData" );
        if (reportSerializable != null) {
            mBinding.drug1.setText( reportSerializable.getDrugOne1() );
            mBinding.drug2.setText( reportSerializable.getDrugTwo1() );
            mBinding.firstDrug1.setText( reportSerializable.getDrugOne1() );
            mBinding.firstDrug2.setText( reportSerializable.getDrugTwo1() );
            mBinding.secondDrug1.setText( reportSerializable.getDrugOne2() );
            mBinding.secondDrug2.setText( reportSerializable.getDrugTwo2() );
            mBinding.firstResult.setText( reportSerializable.getResult1() );
            mBinding.secondResult.setText( reportSerializable.getResult2() );
            mBinding.firstScore.setText( String.valueOf( reportSerializable.getScore1() ) );
            mBinding.secondScore.setText( String.valueOf( reportSerializable.getScore2() ) );
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_left_in, R.anim.translate_right_out);
    }
}
