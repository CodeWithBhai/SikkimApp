package com.eminence.eventit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.eminence.eventit.R;

public class ThinkToDoPlaceActivity extends AppCompatActivity {
    TextView txtPlaceName, txtLocation, txtAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_think_to_do_place);
        getSupportActionBar().hide();

        txtPlaceName = findViewById(R.id.txtPlaceName);
        txtLocation = findViewById(R.id.txtLocation);
        txtAbout = findViewById(R.id.txtAbout);


    }
}