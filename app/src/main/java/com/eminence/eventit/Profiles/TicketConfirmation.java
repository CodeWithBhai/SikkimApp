package com.eminence.eventit.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eminence.eventit.Accounts.LogInActivity;
import com.eminence.eventit.CartModels.BookTicketRequest;
import com.eminence.eventit.CartModels.BookTicketResponse;
import com.eminence.eventit.MainActivity;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.utils.ApiClient;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketConfirmation extends AppCompatActivity implements PaymentResultListener {
    TextView totalPriceShow, totalPriceTicket, gst, bookingFees;
    String totalPricee, totalPriceShoww, UserName, UserEmail, UserMobile, show_gst, show_booking_fees;
    LinearLayout proceedToPay;
    private List<ProfileData> ArrayList;
    int value;
    String user_id ,total_ticket, transaction_id, total_amount, gstt, booking_charges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ticket_confirmation);

        totalPriceTicket = findViewById(R.id.totalPriceTicket);
        totalPriceShow = findViewById(R.id.totalPriceShow);

        proceedToPay = findViewById(R.id.proceedToPay);
        gst = findViewById(R.id.gst);
        bookingFees = findViewById(R.id.bookingFees);

        totalPricee = getIntent().getExtras().getString("total_amount");
        totalPriceShoww = getIntent().getExtras().getString("total_amount");
        show_gst = getIntent().getExtras().getString("gst");
        show_booking_fees = getIntent().getExtras().getString("booking_charges");
        total_ticket = getIntent().getExtras().getString("total_tickets");

        totalPriceTicket.setText(totalPricee);
        totalPriceShow.setText(totalPriceShoww);
        gst.setText(show_gst);
        bookingFees.setText(show_booking_fees);

        SharedPreferences sharedPreferences = this.getApplication().getSharedPreferences("Event_IT", Context.MODE_PRIVATE);
        user_id  = sharedPreferences.getString("UserId", "");
        String userEmail  = sharedPreferences.getString("UserEmail", "");
        String UserName  = sharedPreferences.getString("UserName", "");
        String UserMobile  = sharedPreferences.getString("UserMobile", "");

        getprofile(user_id);

        proceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startpayment(totalPriceShoww);
            }
        });
    }

    private void getprofile(String id) {
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        GetProfileRequest request = new GetProfileRequest(id);
        Call<GetProfileResponse> call = serviceInterface.getProfile(request);
        call.enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    if (status.equals("1")) {
                        ArrayList = response.body().getData();

                        for (int i=0; i<ArrayList.size(); i++){
                            UserEmail = ArrayList.get(i).getEmail();
                            UserName = ArrayList.get(i).getName();
                            UserMobile = ArrayList.get(i).getMobile();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {

            }
        });

    }

    private void startpayment(String totalPriceShoww) {

        String sAmount = String.valueOf(totalPriceShoww);
        int amount = Math.round(Float.parseFloat(sAmount)*100);
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_bMvrd570xHW0bu");
        checkout.setImage(R.drawable.ic_profile_icon);

        JSONObject object = new JSONObject();
        try {
            object.put("name", UserName);
            object.put("description", "Test Mode Payment");
            object.put("theme.color", "#EB0203");
            object.put("currency", "INR");
            object.put("amount", amount);
            object.put("prefill.contact", UserMobile);
            object.put("prefill.email", UserEmail);
            checkout.open(TicketConfirmation.this, object);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String transaction_id) {
        Toast.makeText(this, ""+transaction_id, Toast.LENGTH_SHORT).show();
        startPaymentWithWallet(user_id, total_ticket, transaction_id, totalPriceShoww,  show_gst, show_booking_fees); }

    private void startPaymentWithWallet( String user_id, String total_ticket, String transaction_id, String totalPriceShoww, String gstt, String booking_charges) {
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        BookTicketRequest request = new BookTicketRequest(user_id, total_ticket, transaction_id, totalPriceShoww, gstt, booking_charges);
        Call<BookTicketResponse> call = serviceInterface.bookTicket(request);
        call.enqueue(new Callback<BookTicketResponse>() {
            @Override
            public void onResponse(Call<BookTicketResponse> call, Response<BookTicketResponse> response) {
                if (response.isSuccessful()){
                    String status = String.valueOf(response.body().toString());
                    if (status.equals("1")){
                        Toast.makeText(TicketConfirmation.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BookTicketResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onPaymentError(int i, String s) {

    }

    public void back(View view) {
        onBackPressed();
    }
}