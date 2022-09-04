package com.eminence.eventit.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eminence.eventit.MainActivity;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpSupportActivity extends AppCompatActivity {
    TextView sendQuery,query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_support);
        getSupportActionBar().hide();
        sendQuery = findViewById(R.id.sendQuery);
        query = findViewById(R.id.query);
        SharedPreferences sharedPreferences = getSharedPreferences("Event_IT", Context.MODE_PRIVATE);

        String userLogin  = sharedPreferences.getString("UserId", "");
        sendQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tstquery = query.getText().toString();

                sendQuery(userLogin,tstquery);
            }
        });

    }

    private void sendQuery(String userLogin, String tstquery) {

        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        SendQueryRequest request = new SendQueryRequest(userLogin,tstquery);
        Call<SendQueryResponse> call = serviceInterface.sendQuery(request);
        call.enqueue(new Callback<SendQueryResponse>() {
            @Override
            public void onResponse(Call<SendQueryResponse> call, Response<SendQueryResponse> response) {
                if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    if (status.equals("1")) {
                        Toast.makeText(HelpSupportActivity.this, "sent Query", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(HelpSupportActivity.this, MainActivity.class);
                        intent.putExtra("afterLogin", 1);
                        startActivity(intent);
                        finish();
                    }
                    }
            }

            @Override
            public void onFailure(Call<SendQueryResponse> call, Throwable t) {

            }
        });

    }

    public void back(View view) {
        onBackPressed();
    }
}