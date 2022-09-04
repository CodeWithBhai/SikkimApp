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

public class CategorySport extends AppCompatActivity {

    ImageView searchActivity;
    ImageView back_img;
    RecyclerView categorySportRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_sport);
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
        categorySportRecycler = findViewById(R.id.categorySportRecycler);
        ArrayList<AdvantureModel> list = new ArrayList<>();

        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));

        AdvantureAdapter adapter = new AdvantureAdapter(list, this);
        categorySportRecycler.setAdapter(adapter);

       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_TopPlaces.setLayoutManager(layoutManager);*/

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        categorySportRecycler.setLayoutManager(gridLayoutManager);
    }
}