package com.eminence.eventit.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.eminence.eventit.R;

public class MyLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);
        getSupportActionBar().hide();
    }

    public void back(View view) {
        onBackPressed();
    }
}