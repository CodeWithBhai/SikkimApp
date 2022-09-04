package com.eminence.eventit.Partner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eminence.eventit.Partner.Marchant.MarchantprofileDetails;
import com.eminence.eventit.R;

public class MarchantAllBooking extends AppCompatActivity{

    LinearLayout filter, ticketOrderBooking;
    TextView filter1, filter2, filter3;
    ImageView marchentAccountPage;

    String[] courses = { "C", "Data structures",
            "Interview prep", "Algorithms",
            "DSA with java", "OS" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marchant_all_booking);
        getSupportActionBar().hide();

        //Spinner spin = findViewById(R.id.coursesspinner);
        filter = findViewById(R.id.filter);
        filter1 = findViewById(R.id.filter1);
        filter2 = findViewById(R.id.filter2);
        filter3 = findViewById(R.id.filter3);
        marchentAccountPage = findViewById(R.id.marchentAccountPage);
        ticketOrderBooking = findViewById(R.id.ticketOrderBooking);

        /*spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);*/

        filter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View dialogBox = LayoutInflater.from(view.getContext()).inflate(R.layout.filter_dialog_box, null);
                builder.setView(dialogBox);
                builder.setCancelable(true);
                builder.show();
            }
        });
        ticketOrderBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StaffBookingOrderDetailsActivity.class);
                startActivity(intent);
            }
        });
        marchentAccountPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MarchantprofileDetails.class);
                startActivity(intent);
            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View dialogBox = LayoutInflater.from(view.getContext()).inflate(R.layout.filter_dialog_box, null);
                builder.setView(dialogBox);
                builder.setCancelable(true);
                builder.show();
            }
        });
        filter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View dialogBox = LayoutInflater.from(view.getContext()).inflate(R.layout.filter_dialog_box, null);
                builder.setView(dialogBox);
                builder.setCancelable(true);
                builder.show();
            }
        });
        filter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View dialogBox = LayoutInflater.from(view.getContext()).inflate(R.layout.filter_dialog_box, null);
                builder.setView(dialogBox);
                builder.setCancelable(false);
                builder.show();
            }
        });

    }

    /*@Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),
                courses[i],
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/

    public void back(View view) {
        onBackPressed();
    }
}