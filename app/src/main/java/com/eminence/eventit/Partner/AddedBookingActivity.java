package com.eminence.eventit.Partner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eminence.eventit.Activities.details.PlaceDetailTotalRequest;
import com.eminence.eventit.Activities.details.PlaceDetailTotalResponse;
import com.eminence.eventit.CartModels.CartListRequest;
import com.eminence.eventit.CartModels.CartListResponse;
import com.eminence.eventit.CartModels.CartListResponseData;
import com.eminence.eventit.CartModels.Getcartamt;
import com.eminence.eventit.Models.EventCartAdapter;
import com.eminence.eventit.Profiles.TicketConfirmation;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.utils.ApiClient;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddedBookingActivity extends AppCompatActivity implements Getcartamt {
    LinearLayout goToCheckOut, lightSound;
    RecyclerView addBookingRecycleView,priceListRecycleView;
    String user_id, total, total_ticket;
    TextView totalprice, totalPriceShow, bookingFees, gst, totalPriceTicket;
    String totalAmt ="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_booking);
        getSupportActionBar().hide();
        priceListRecycleView = findViewById(R.id.priceListRecycleView);
        goToCheckOut = findViewById(R.id.goToCheckOut);
        totalprice = findViewById(R.id.totalprice);
        totalPriceShow = findViewById(R.id.totalPriceShow);
        lightSound = findViewById(R.id.lightSound);
        bookingFees = findViewById(R.id.bookingFees);
        gst = findViewById(R.id.gst);
        totalPriceTicket = findViewById(R.id.totalPriceTicket);
        addBookingRecycleView = findViewById(R.id.addBookingRecycleView);

        SharedPreferences sharedPreferences = this.getApplication().getSharedPreferences("Event_IT", Context.MODE_PRIVATE);
        String id  = sharedPreferences.getString("UserId", "");
        String userEmail  = sharedPreferences.getString("UserEmail", "");
        String UserName  = sharedPreferences.getString("UserName", "");
        String UserMobile  = sharedPreferences.getString("UserMobile", "");
        getcartist(id);
        get_total_cart_amount(id);

        goToCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddedBookingActivity.this, TicketConfirmation.class);
                intent.putExtra("sub_total", totalprice.getText().toString());
                intent.putExtra("total_amount", totalPriceShow.getText().toString());
                intent.putExtra("gst", gst.getText().toString());
                intent.putExtra("booking_charges", bookingFees.getText().toString());
                intent.putExtra("total_amount", totalPriceTicket.getText().toString());
                intent.putExtra("total_tickets", total_ticket);
                startActivity(intent);
            }
        });
    }

    private void getcartist(String userId) {
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        CartListRequest request = new CartListRequest(userId);
        Call<CartListResponse> call = serviceInterface.cartList(request);
        call.enqueue(new Callback<CartListResponse>() {
            @Override
            public void onResponse(Call<CartListResponse> call, Response<CartListResponse> response) {
                if (response.isSuccessful()){
                    String status = response.body().getStatus().toString();
                    if (status.equals("1")){
                        addBookingRecycleView.setVisibility(View.VISIBLE);
                       // lightSound.setVisibility(View.VISIBLE);
                        List<CartListResponseData> list = response.body().getData();
                        bindcartlist(list); }
                }
            }
            @Override
            public void onFailure(Call<CartListResponse> call, Throwable t) {

            }
        });
    }

    private void bindcartlist(List<CartListResponseData> list) {
        EventCartAdapter adapter = new EventCartAdapter(list, AddedBookingActivity.this, this);
        //Toast.makeText(this, "something showing", Toast.LENGTH_SHORT).show();
        addBookingRecycleView.setHasFixedSize(true);
        addBookingRecycleView.setLayoutManager(new LinearLayoutManager(AddedBookingActivity.this, LinearLayoutManager.VERTICAL, false));
        addBookingRecycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void total_amt() {
        getcartist(user_id);
    }

    private void get_total_cart_amount(String userId) {
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        PlaceDetailTotalRequest request = new PlaceDetailTotalRequest(userId);
        Call<PlaceDetailTotalResponse> call = serviceInterface.getPlaceDetailsTotal(request);
        call.enqueue(new Callback<PlaceDetailTotalResponse>() {
            @Override
            public void onResponse(Call<PlaceDetailTotalResponse> call, Response<PlaceDetailTotalResponse> response) {
                if (response.isSuccessful()){
                    String status = response.body().getStatus().toString();
                    if (status.equals("1")) {
                        String gstAmt = response.body().getGst();
                        totalAmt = response.body().getTotal_amount();
                        total_ticket = response.body().getTotal_tickets();
                        String subTotalAmt = response.body().getSub_total();
                        String bookingAmt = response.body().getBooking_charges();
                        total = response.body().getTotal_amount();

                        totalPriceShow.setText(""+total);
                        totalprice.setText(""+subTotalAmt);
                        gst.setText(""+gstAmt);
                        bookingFees.setText(""+bookingAmt);
                        totalPriceTicket.setText(""+total);
                        Toast.makeText(AddedBookingActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PlaceDetailTotalResponse> call, Throwable t) {

            }
        });

    }
}