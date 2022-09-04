package com.eminence.eventit.Partner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.eminence.eventit.R;

public class StaffDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_details);
        getSupportActionBar().hide();
    }

    public void back(View view) {
        onBackPressed();
    }
}