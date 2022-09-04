package com.eminence.eventit.Partner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eminence.eventit.R;

public class MyStaffActivity extends AppCompatActivity {
    TextView addStaffForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_staff);
        getSupportActionBar().hide();

        addStaffForm = findViewById(R.id.addStaffForm);

        addStaffForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddStaffFormActivity.class);
                startActivity(intent);
            }
        });

    }

    public void back(View view) {
        onBackPressed();
    }
}