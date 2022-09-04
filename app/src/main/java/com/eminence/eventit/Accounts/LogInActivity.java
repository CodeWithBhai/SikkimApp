package com.eminence.eventit.Accounts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eminence.eventit.MainActivity;
import com.eminence.eventit.Partner.MarchantAllBooking;
import com.eminence.eventit.Profiles.EditProfileActivity;
import com.eminence.eventit.Profiles.GetProfileRequest;
import com.eminence.eventit.Profiles.GetProfileResponse;
import com.eminence.eventit.Profiles.ProfileData;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventListData;
import com.eminence.eventit.utils.ApiClient;
import com.eminence.eventit.utils.PreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {
    TextView skip;
    String  logid;
    String mobileno,otp;
    SharedPreferences sharedpreferences;
    TextView registerAccount, loginAccountMain,verifyOTP;
    EditText input1, input2, input3, input4,input5,input6,txtmobile;
    private List<ProfileData> ArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();
        //skip = findViewById(R.id.skip);
        loginAccountMain = findViewById(R.id.loginAccountMain);
        verifyOTP = findViewById(R.id.registerAcount);

        registerAccount = findViewById(R.id.loginAnAccount);
        txtmobile = findViewById(R.id.editTextPhone2);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        input4 = findViewById(R.id.input4);
        input5 = findViewById(R.id.input5);
        input6 = findViewById(R.id.input6);


        setUpOtpInput();

        loginAccountMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyOTP();

            }
        });

        registerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, CreateAcountActivity.class);
                startActivity(intent);
                finish();


            }
        });

        verifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 mobileno = String.valueOf(txtmobile.getText());
               if (CheckAllFields()){
                getOTP();}
            }
        });
    }

    private void verifyOTP() {
        otp = input1.getText().toString() + input2.getText().toString() + input3.getText().toString() + input4.getText().toString()+ input5.getText().toString()+ input6.getText().toString();
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        VerifyOtpRequest request = new VerifyOtpRequest(logid,otp,mobileno);
        Call<VerifyOtpResponse> call = serviceInterface.verifyOTP(request);
        call.enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    String id = String.valueOf(response.body().getUser_id());
                    String email = String.valueOf(response.body().getUser_email());

                    if (status.equals("1")) {
                        getprofile(id);

                        finish();
                    }
                    else {
                        Toast.makeText(LogInActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {

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

                        sharedpreferences = getSharedPreferences("Event_IT", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("UserId", id);
                        editor.putString("UserEmail", ArrayList.get(0).getEmail());
                        editor.putString("UserName", ArrayList.get(0).getName());
                        editor.putString("UserMobile", ArrayList.get(0).getMobile());
                        editor.putString("UserAddres", ArrayList.get(0).getAddress());
                        editor.commit();


                        Toast.makeText(LogInActivity.this, " login  Succesfull", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                        intent.putExtra("afterLogin", 1);
                        startActivity(intent);
                        finish();

                    }

                }

            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {

            }
        });

    }

    private void getOTP() {
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        GetOtpRequest request = new GetOtpRequest(mobileno);
        Call<GetOtpResponse> call = serviceInterface.getOTP(request);
        call.enqueue(new Callback<GetOtpResponse>() {
            @Override
            public void onResponse(Call<GetOtpResponse> call, Response<GetOtpResponse> response) {
                if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    String message = String.valueOf(response.body().getMessage());

                    logid=  response.body().getLogid();
                    if (status.equals("1")) {
                        loginAccountMain.setBackgroundResource(R.color.red);
                        verifyOTP.setVisibility(View.GONE);
                        input1.requestFocus();

                    }else {
                        Toast.makeText(LogInActivity.this, "Not Registered Please Create New Account", Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<GetOtpResponse> call, Throwable t) {

            }
        });

    }
    private boolean CheckAllFields() {
        if (txtmobile.length() < 10||txtmobile.length()>10) {
            txtmobile.setError("Please Enter 10 digit Number");
            return false;
        }
    return true;
    }

    private void setUpOtpInput() {
        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    input2.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    input3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    input4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    input5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    input6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void back(View view) {
        onBackPressed();
    }
}