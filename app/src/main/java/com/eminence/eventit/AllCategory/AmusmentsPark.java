package com.eminence.eventit.AllCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.eminence.eventit.Adapters.AdvantureAdapter;
import com.eminence.eventit.Models.AdvantureModel;
import com.eminence.eventit.R;
import com.eminence.eventit.SearchActivity;

import java.util.ArrayList;

public class AmusmentsPark extends AppCompatActivity {

    ImageView searchActivity;
    ImageView back_img;
    RecyclerView categoryAmusmentPark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amusments_park);
        getSupportActionBar().hide();

        back_img = findViewById(R.id.back_img);
        searchActivity = findViewById(R.id.searchActivity);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        categoryAmusmentPark = findViewById(R.id.categoryAmusmentPark);
        ArrayList<AdvantureModel> list = new ArrayList<>();

        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));

        AdvantureAdapter adapter = new AdvantureAdapter(list, this);
        categoryAmusmentPark.setAdapter(adapter);

       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_TopPlaces.setLayoutManager(layoutManager);*/

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        categoryAmusmentPark.setLayoutManager(gridLayoutManager);
    }
}