package com.eminence.eventit.Partner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eminence.eventit.Profiles.StaffProfileActivity;
import com.eminence.eventit.QrScan.QrScanActivity;
import com.eminence.eventit.R;

public class StaffAllTicketScannerActivity extends AppCompatActivity {
    LinearLayout ticketDetailsOrder, scanQRCode;
    ImageView editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_all_ticket_scanner);
        getSupportActionBar().hide();

        ticketDetailsOrder = findViewById(R.id.ticketDetailsOrder);
        editProfile = findViewById(R.id.editProfile);
        scanQRCode = findViewById(R.id.scanQRCode);

        ticketDetailsOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TicketBookingDetailsActivity.class);
                startActivity(intent);
            }
        });
        scanQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QrScanActivity.class);
                startActivity(intent);
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
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