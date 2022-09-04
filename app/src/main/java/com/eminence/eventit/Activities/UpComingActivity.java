package com.eminence.eventit.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.eminence.eventit.Adapters.UpComingAdapter;
import com.eminence.eventit.Models.UpComingModel;
import com.eminence.eventit.R;

import java.util.ArrayList;

public class UpComingActivity extends AppCompatActivity {
    RecyclerView up_coming_recyclerView;
    Context context;
    ArrayList<UpComingModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming);
        getSupportActionBar().hide();

        up_coming_recyclerView = findViewById(R.id.up_coming_recyclerView);

        UpComingAdapter adapter = new UpComingAdapter(arrayList, this);
        up_coming_recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        up_coming_recyclerView.setLayoutManager(linearLayoutManager);
    }
}