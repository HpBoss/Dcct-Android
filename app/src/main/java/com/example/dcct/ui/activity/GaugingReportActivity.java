package com.example.dcct.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.dcct.R;
import com.example.dcct.model.internet.model.ReportSerializable;

public class GaugingReportActivity extends AppCompatActivity {

    private TextView drug1,drug2;
    private TextView firstDrug1,firstDrug2;
    private TextView secondDrug1,secondDrug2;
    private TextView firstResult,secondResult;
    private TextView firstScore,secondScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauging_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");//必须加上
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener( v -> onBackPressed() );
        initView();
        getGaugingReport();
    }

    private void initView() {
        drug1 = findViewById( R.id.drug1 );
        drug2 = findViewById( R.id.drug2 );
        firstDrug1 = findViewById( R.id.firstDrug1 );
        firstDrug2 = findViewById( R.id.firstDrug2 );
        secondDrug1 = findViewById( R.id.secondDrug1 );
        secondDrug2 = findViewById( R.id.secondDrug2 );
        firstResult = findViewById( R.id.firstResult );
        secondResult = findViewById( R.id.secondResult );
        firstScore = findViewById( R.id.firstScore );
        secondScore = findViewById( R.id.secondScore );
    }

    private void getGaugingReport() {
        ReportSerializable reportSerializable = (ReportSerializable) getIntent().getSerializableExtra( "queryData" );
        if (reportSerializable != null) {
            drug1.setText( reportSerializable.getDrugOne1() );
            drug2.setText( reportSerializable.getDrugTwo1() );
            firstDrug1.setText( reportSerializable.getDrugOne1() );
            firstDrug2.setText( reportSerializable.getDrugTwo1() );
            secondDrug1.setText( reportSerializable.getDrugOne2() );
            secondDrug2.setText( reportSerializable.getDrugTwo2() );
            firstResult.setText( reportSerializable.getResult1() );
            secondResult.setText( reportSerializable.getResult2() );
            firstScore.setText( String.valueOf( reportSerializable.getScore1() ) );
            secondScore.setText( String.valueOf( reportSerializable.getScore2() ) );
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_left_in, R.anim.translate_right_out);
    }
}
