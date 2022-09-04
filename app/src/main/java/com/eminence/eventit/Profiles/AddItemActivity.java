package com.eminence.eventit.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.eminence.eventit.R;

public class AddItemActivity extends AppCompatActivity {
    ImageView proceedToPaymentFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getSupportActionBar().hide();

        proceedToPaymentFinal= findViewById(R.id.proceedToPaymentFinal);
        proceedToPaymentFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItemActivity.this, TicketConfirmation.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }
}