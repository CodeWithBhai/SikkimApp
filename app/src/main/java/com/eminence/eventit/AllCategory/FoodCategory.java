package com.eminence.eventit.AllCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eminence.eventit.Adapters.AdvantureAdapter;
import com.eminence.eventit.Models.AdvantureModel;
import com.eminence.eventit.R;

import java.util.ArrayList;

public class FoodCategory extends AppCompatActivity {
    ImageView back_img;
    LinearLayout categoryAbout;
    RecyclerView categoryFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);
        getSupportActionBar().hide();

        categoryFood= findViewById(R.id.categoryFood);
        back_img= findViewById(R.id.back_img);

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

        ArrayList<AdvantureModel> list = new ArrayList<>();

        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));
        list.add(new AdvantureModel(R.drawable.animal_img, "India", "Romania"));

        AdvantureAdapter adapter = new AdvantureAdapter(list, this);
        categoryFood.setAdapter(adapter);

       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_TopPlaces.setLayoutManager(layoutManager);*/

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        categoryFood.setLayoutManager(gridLayoutManager);
    }
}