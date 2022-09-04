package com.eminence.eventit.Partner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eminence.eventit.Profiles.StaffProfileActivity;
import com.eminence.eventit.R;

public class AddStaffFormActivity extends AppCompatActivity {
    TextView continueAddStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_form);
        getSupportActionBar().hide();

        continueAddStaff = findViewById(R.id.continueAddStaff);

        continueAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StaffList.class);
                startActivity(intent);
            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }
}