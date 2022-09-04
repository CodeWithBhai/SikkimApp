package com.eminence.eventit.Accounts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eminence.eventit.Models.OrderDetails.OrderDetailsRequest;
import com.eminence.eventit.Models.OrderDetails.OrderDetailsResponse;
import com.eminence.eventit.Models.OrderDetails.OrderDetailsResponseData;
import com.eminence.eventit.Partner.MarchantAllBooking;
import com.eminence.eventit.Profiles.AddItemActivity;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.utils.ApiClient;
import com.eminence.eventit.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketHistoryActivity extends AppCompatActivity {
    ImageView proceedToPayConfirm, ticketQRImg, img_ev;
    CardView marchantPage;
    String id;
    TextView ticketHead, orderNumber, bookingDate, categoryName, totalTicket, ticketPrice, gstPrice, bookingFeesPrice, totalAmount,
            activityQty, ticketQRId, addOnServiceName, totalAddon, addOnPrice;

    LinearLayout addOnService;

    List<OrderDetailsResponseData> ArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_history);
        getSupportActionBar().hide();
        String OrderNo = getIntent().getStringExtra("OrderNo");
        addOnServiceName = findViewById(R.id.addOnServiceName);
        totalAddon = findViewById(R.id.totalAddon);
        addOnPrice = findViewById(R.id.addOnPrice);
        ticketHead = findViewById(R.id.ticketHead);
        orderNumber = findViewById(R.id.orderNumber);
        bookingDate = findViewById(R.id.bookingDate);
        categoryName = findViewById(R.id.categoryName);
        totalTicket = findViewById(R.id.totalTicket);
        ticketPrice = findViewById(R.id.ticketPrice);
        gstPrice = findViewById(R.id.gstPrice);
        bookingFeesPrice = findViewById(R.id.bookingFeesPrice);
        totalAmount = findViewById(R.id.totalAmount);
       // activityQty = findViewById(R.id.activityQty);
        ticketQRId = findViewById(R.id.ticketQRId);
        ticketQRImg = findViewById(R.id.ticketQRImg);
        marchantPage = findViewById(R.id.marchantPage);
        img_ev = findViewById(R.id.img_ev);

        ticketHead.setText(" Od: "+OrderNo);

        orderDetails(OrderNo);
        marchantPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MarchantAllBooking.class);
                startActivity(intent);
                finish();
            }
        });

       /* proceedToPayConfirm = findViewById(R.id.proceedToPayConfirm);
        proceedToPayConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicketHistoryActivity.this, AddItemActivity.class);
                startActivity(intent);
                finish();
            }
        });*/
    }

    private void orderDetails(String orderNo) {

        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        OrderDetailsRequest request = new OrderDetailsRequest("SIKKIM_OD0059");
        Call<OrderDetailsResponse> call = serviceInterface.orderDetails(request);
        call.enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
               if (response.isSuccessful()){
                 //  String status = String.valueOf(response.body().getStatus());
                   if (response.body().getStatus().equals("1")){
                       Glide.with(TicketHistoryActivity.this)
                               .load(Constants.IMAGEURL+ response.body().getPlace_image())
                               .into(img_ev);
                       ticketHead.setText(response.body().getOrder_unique_id());
                       orderNumber.setText(response.body().getOrder_unique_id());
                       bookingDate.setText(response.body().getCreated_at());
                       categoryName.setText(response.body().getCategory_name());
                       ticketPrice.setText(response.body().getTicket_total_amount());
                       gstPrice.setText(response.body().getGst());
                       bookingFeesPrice.setText(response.body().getBooking_charges());
                       totalAmount.setText(response.body().getTotal_amount());
                       totalTicket.setText(response.body().getTotal_tickets());

                     int addOnNo = Integer.parseInt(response.body().getTotal_addon());
                     if (addOnNo>=1){
                         addOnService.setVisibility(View.VISIBLE);
                         totalAddon.setText("X"+response.body().getTotal_addon());
                         addOnPrice.setText(response.body().getTotal_amount());
                     }

                       ArrayList = response.body().getDetails();
                       bindTicketList(ArrayList);
                   }
               }
            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {
            }
        });
    }

    private void bindTicketList(List<OrderDetailsResponseData> arrayList) {
        Toast.makeText(this, "QR Code Generate", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        onBackPressed();
    }
}