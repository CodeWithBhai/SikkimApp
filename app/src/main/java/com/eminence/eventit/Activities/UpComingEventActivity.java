package com.eminence.eventit.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;

import com.eminence.eventit.Adapters.TopVisitedGridAdapter;
import com.eminence.eventit.Adapters.UpComingGridAdapter;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.ui.home.model.upcoming.UpComingEventRequest;
import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventListData;
import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventResponse;
import com.eminence.eventit.utils.ApiClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpComingEventActivity extends AppCompatActivity {

    RecyclerView recycler_view_TopPlaces;
    private List<UpcomingEventListData> ArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_event);
        getSupportActionBar().hide();

        recycler_view_TopPlaces = findViewById(R.id.recycler_view_TopPlaces);
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        UpComingEventRequest request = new UpComingEventRequest("Sikkim");
        Call<UpcomingEventResponse> call = serviceInterface.getUpComingEventList(request);
        call.enqueue(new Callback<UpcomingEventResponse>() {
            @Override
            public void onResponse(Call<UpcomingEventResponse> call, Response<UpcomingEventResponse> response) {
                if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    ArrayList = response.body().getData();
                    if (status.equals("1")) {
                        bindUpComingList(ArrayList);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpcomingEventResponse> call, Throwable t) {

            }
        });
    }

    private void bindUpComingList(List<UpcomingEventListData> arrayList) {
        UpComingGridAdapter adapter = new UpComingGridAdapter(this,arrayList);
        recycler_view_TopPlaces.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recycler_view_TopPlaces.setLayoutManager(gridLayoutManager);
        recycler_view_TopPlaces.setAdapter(adapter);
    }

    public void back(View view) {
        onBackPressed();
    }
}