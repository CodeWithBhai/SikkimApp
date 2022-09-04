package com.eminence.eventit.Partner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.eminence.eventit.R;

public class StaffList extends AppCompatActivity {

    LinearLayout confirmStaffTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);
        getSupportActionBar().hide();
        confirmStaffTicket = findViewById(R.id.confirmStaffTicket);

        confirmStaffTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StaffDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }
}