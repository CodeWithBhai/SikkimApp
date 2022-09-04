package com.eminence.eventit.QrScan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.eminence.eventit.R;

public class QrScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);
        getSupportActionBar().hide();
    }
    public void back(View view) {
        onBackPressed();
    }
}