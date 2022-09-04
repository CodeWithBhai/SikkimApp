package com.eminence.eventit.AllCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eminence.eventit.Adapters.AdvantureAdapter;
import com.eminence.eventit.Models.AdvantureModel;
import com.eminence.eventit.R;
import com.eminence.eventit.SearchActivity;

import java.util.ArrayList;

public class MusicCategory extends AppCompatActivity {

    LinearLayout categoryAbout;


    ImageView searchActivity;
    ImageView back_img;
    RecyclerView categoryMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_category);
        getSupportActionBar().hide();

        back_img = findViewById(R.id.back_img);
        searchActivity = findViewById(R.id.searchActivity);

       /* categoryAbout = findViewById(R.id.categoryAbout);
        categoryAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlaceDetails.class);
                startActivity(intent);
            }
        });*/

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
        categoryMusic = findViewById(R.id.categoryMusic);
        ArrayList<AdvantureModel> list = new ArrayList<>();

        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));

        AdvantureAdapter adapter = new AdvantureAdapter(list, this);
        categoryMusic.setAdapter(adapter);

       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_TopPlaces.setLayoutManager(layoutManager);*/

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        categoryMusic.setLayoutManager(gridLayoutManager);
    }
}