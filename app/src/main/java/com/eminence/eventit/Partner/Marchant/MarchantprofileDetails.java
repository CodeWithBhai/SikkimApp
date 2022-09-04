package com.eminence.eventit.Partner.Marchant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.eminence.eventit.Partner.MyStaffActivity;
import com.eminence.eventit.Profiles.AboutUsActivity;
import com.eminence.eventit.Profiles.AddItemActivity;
import com.eminence.eventit.Profiles.HelpSupportActivity;
import com.eminence.eventit.Profiles.StaffProfileActivity;
import com.eminence.eventit.R;

public class MarchantprofileDetails extends AppCompatActivity {

    LinearLayout myStaff, bookingHistoryMerchant, helpSupplyMerchant, aboutUsMerchant, merchantProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marchantprofile_details);
        getSupportActionBar().hide();

        myStaff = findViewById(R.id.myStaff);
        aboutUsMerchant = findViewById(R.id.aboutUsMerchant);
        helpSupplyMerchant = findViewById(R.id.helpSupplyMerchant);
        bookingHistoryMerchant = findViewById(R.id.bookingHistoryMerchant);
        merchantProfile = findViewById(R.id.merchantProfile);
        myStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyStaffActivity.class);
                startActivity(intent);
            }
        });
        bookingHistoryMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MerchantBookingHistory.class);
                startActivity(intent);
            }
        });
        aboutUsMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        helpSupplyMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpSupportActivity.class);
                startActivity(intent);
            }
        });
        merchantProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StaffProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    public void back(View view) {
        onBackPressed();
    }
}