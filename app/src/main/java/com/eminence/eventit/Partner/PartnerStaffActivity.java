package com.eminence.eventit.Partner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eminence.eventit.Accounts.LogInActivity;
import com.eminence.eventit.R;

public class PartnerStaffActivity extends AppCompatActivity {

    TextView staffLogin, registerAcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_staff);
        getSupportActionBar().hide();

        staffLogin = findViewById(R.id.staffLogin);
        registerAcount = findViewById(R.id.registerAcount);
        registerAcount = findViewById(R.id.registerAcount);
        staffLogin .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MarchantAllBooking.class);
                startActivity(intent);
            }
        });
        registerAcount .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }
}