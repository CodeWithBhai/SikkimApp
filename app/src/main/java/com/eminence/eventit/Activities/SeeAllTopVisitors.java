package com.eminence.eventit.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.eminence.eventit.Adapters.TopVisitedAdapter;
import com.eminence.eventit.Adapters.TopVisitedGridAdapter;
import com.eminence.eventit.MainActivity;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.ui.home.model.slider.SliderData;
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedListData;
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedRequest;
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedResponse;
import com.eminence.eventit.utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllTopVisitors extends AppCompatActivity {
    RecyclerView recycler_view_TopPlaces;
    private List<TopVisitedListData> ArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_top_visitors);
        getSupportActionBar().hide();

        recycler_view_TopPlaces = findViewById(R.id.recycler_view_TopPlaces);

   //     ArrayList.clear();
        // api calling

        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        TopVisitedRequest request = new TopVisitedRequest("Sikkim");
        Call<TopVisitedResponse> call = serviceInterface.getTopList(request);
        call.enqueue(new Callback<TopVisitedResponse>() {
            @Override
            public void onResponse(Call<TopVisitedResponse> call, Response<TopVisitedResponse> response) {
              if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    ArrayList = response.body().getData();
                    if (status.equals("1")) {
                        bindTopVisitedList(ArrayList);



                    }
                }



            }

            @Override
            public void onFailure(Call<TopVisitedResponse> call, Throwable t) {

            }
        });


    }

    private void bindTopVisitedList(List<TopVisitedListData> arrayList) {
        TopVisitedGridAdapter adapter = new TopVisitedGridAdapter(this,arrayList);
        recycler_view_TopPlaces.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycler_view_TopPlaces.setLayoutManager(gridLayoutManager);
        recycler_view_TopPlaces.setAdapter(adapter);


    }

    public void back(View view) {
        onBackPressed();
    }
}