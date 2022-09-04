package com.eminence.eventit.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eminence.eventit.Accounts.LogInActivity;
import com.eminence.eventit.MainActivity;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.utils.ApiClient;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {
    RatingBar ratingBar;
    EditText feedback;
    TextView txtUserName;
    Button sendFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();
        feedback=findViewById(R.id.feedback);
        ratingBar=findViewById(R.id.ratingBar);
        sendFeedback=findViewById(R.id.sendFeedback);
        txtUserName = findViewById(R.id.txtUserName);
        SharedPreferences sharedPreferences = getSharedPreferences("Event_IT", Context.MODE_PRIVATE);
        String userLogin  = sharedPreferences.getString("UserId", "");
        String UserName  = sharedPreferences.getString("UserName", "");
        txtUserName.setText("Hi " +UserName);
         ratingBar.setRating(0);
        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = ""+ratingBar.getRating();
                String feed = feedback.getText().toString();
                if(CheckAllFields()){
                    if (userLogin.equals("")){
                        Intent intent = new Intent(FeedbackActivity.this, LogInActivity.class);
                        startActivity(intent);
                        Toast.makeText(FeedbackActivity.this, "Please Login For Feedback", Toast.LENGTH_SHORT).show();
                    }else {
                    sendFeedback(userLogin,rating,feed);}

                }
            }
        });


    }
    private boolean CheckAllFields() {

        if (ratingBar.getRating() < 1) {
            Toast.makeText(FeedbackActivity.this, "Rating Cannot Be Empty", Toast.LENGTH_SHORT).show();

            return false;

        }
        if(feedback.length()<1){
            feedback.requestFocus();
            feedback.setError("Please enter Valid Feedback");
            return false;
        }

        return true;
    }


    private void sendFeedback(String userLogin, String rating, String feed) {
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        SendFeedbackRequest request = new SendFeedbackRequest(userLogin,rating,feed);
        Call<SendFeedbackResponse> call = serviceInterface.sendFeedback(request);
        call.enqueue(new Callback<SendFeedbackResponse>() {
            @Override
            public void onResponse(Call<SendFeedbackResponse> call, Response<SendFeedbackResponse> response) {
                if (response.isSuccessful()) {

                }
                    String status = String.valueOf(response.body().getStatus());
                    if (status.equals("1")) {
                        Toast.makeText(FeedbackActivity.this, "sent Feedback", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FeedbackActivity.this, MainActivity.class);
                        intent.putExtra("afterLogin", 1);
                        startActivity(intent);
                        finish();
                    }
            }

            @Override
            public void onFailure(Call<SendFeedbackResponse> call, Throwable t) {

            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }
}