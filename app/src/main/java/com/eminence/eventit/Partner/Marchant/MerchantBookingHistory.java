package com.eminence.eventit.Partner.Marchant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.eminence.eventit.R;

public class MerchantBookingHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_booking_history);
        getSupportActionBar().hide();
    }
    public void back(View view) {
        onBackPressed();
    }
}