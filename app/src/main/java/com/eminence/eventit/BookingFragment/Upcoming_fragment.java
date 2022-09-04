package com.eminence.eventit.BookingFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eminence.eventit.Accounts.TicketHistoryActivity;
import com.eminence.eventit.Adapters.MyTicketAdapters.MyTicketAdapter;
import com.eminence.eventit.Adapters.UpComingAdapter;
import com.eminence.eventit.Models.MyTicketModels.MyTicketRequest;
import com.eminence.eventit.Models.MyTicketModels.MyTicketResponse;
import com.eminence.eventit.Models.MyTicketModels.MyTicketResponseData;
import com.eminence.eventit.Models.UpComingModel;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upcoming_fragment extends Fragment {
    private List<MyTicketResponseData> ArrayList;
    RecyclerView upcomingRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_fragment, container, false);

        upcomingRecyclerView = view.findViewById(R.id.upcomingRecyclerView);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Event_IT", Context.MODE_PRIVATE);
        String userLogin  = sharedPreferences.getString("UserId", "");

       // getMyOrder(userLogin);

        return view;
    }

    private void getMyOrder(String userLogin) {
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        MyTicketRequest request = new MyTicketRequest(userLogin, "upcoming");
        Call<MyTicketResponse> call = serviceInterface.myOrder(request);
        call.enqueue(new Callback<MyTicketResponse>() {
            @Override
            public void onResponse(Call<MyTicketResponse> call, Response<MyTicketResponse> response) {
                if (response.isSuccessful()){
                    String status = String.valueOf(response.body().getStatus());
                    if (status.equals("1")) {
                        ArrayList = response.body().getData();
                        Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        MyTicketAdapter adapter = new MyTicketAdapter(ArrayList, getActivity());
                        upcomingRecyclerView.setHasFixedSize(true);
                        upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        upcomingRecyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<MyTicketResponse> call, Throwable t) {

            }
        });
    }
}