package com.eminence.eventit.Accounts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eminence.eventit.MainActivity;
import com.eminence.eventit.Profiles.GetProfileRequest;
import com.eminence.eventit.Profiles.GetProfileResponse;
import com.eminence.eventit.Profiles.ProfileData;
import com.eminence.eventit.Profiles.VerifySignUpOtpRequest;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.utils.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAcountActivity extends AppCompatActivity {
    TextView skip;
    String mobileno;
    String  logid,otp;
    private List<ProfileData> ArrayList;


    TextView loginAnAccount, registerAcount;
    EditText input1, input2, input3, input4,input5,input6;
    EditText name, gmail, number;
    Button verifyBUtton;
    SharedPreferences sharedpreferences;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount);
        getSupportActionBar().hide();

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        input4 = findViewById(R.id.input4);

        input5 = findViewById(R.id.input5);
        input6 = findViewById(R.id.input6);

        setUpOtpInput();

        //skip = findViewById(R.id.skip);
        loginAnAccount = findViewById(R.id.loginAnAccount);
        verifyBUtton = findViewById(R.id.verifyBUtton);
        registerAcount = findViewById(R.id.registerAcount);
        name = findViewById(R.id.name);
        gmail = findViewById(R.id.gmail);
        number = findViewById(R.id.number);

       /* skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAcountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
        verifyBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           mobileno=      number.getText().toString();
           if (CheckAllFields()){

                getOTP(mobileno);}


            }
        });
        registerAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              verifyOTP(gmail.getText().toString(),name.getText().toString());
            }
        });
        loginAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAcountActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        loginAnAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validateMobile(number.getText().toString())){
                    loginAnAccount.setEnabled(true);
                }else {
                    loginAnAccount.setEnabled(false);
                    loginAnAccount.setError("Valid Mobile Number");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

       /* registerAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number.getText().toString().matches(MobilePattern)) {

                    Toast.makeText(getApplicationContext(), "phone number is valid", Toast.LENGTH_SHORT).show();
                    return true;
                }
               else if(gmail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }else {
                    if (gmail.getText().toString().trim().matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/
    }

    private void getOTP(String mobileno) {
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        GetOtpRequest request = new GetOtpRequest(mobileno);
        Call<GetOtpResponse> call = serviceInterface.getSignUpOTP(request);
        call.enqueue(new Callback<GetOtpResponse>() {
            @Override
            public void onResponse(Call<GetOtpResponse> call, Response<GetOtpResponse> response) {
                if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    String message = String.valueOf(response.body().getMessage());

                    logid=  response.body().getLogid();
                    if (status.equals("1")) {
                        verifyBUtton.setVisibility(View.GONE);
                        registerAcount.setBackgroundResource(R.color.red);
                        input1.requestFocus();


                    }else{

                        Toast.makeText(CreateAcountActivity.this, message+ ",Please Login!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<GetOtpResponse> call, Throwable t) {

            }
        });

    }

    private boolean CheckAllFields() {
        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (number.length() < 10||number.length()>10) {
            number.setError("Please Enter 10 digit Number");
            number.requestFocus();

            return false;
        }
        if(name.length()<3){
            name.requestFocus();

            name.setError("Please enter Valid Name");

            return false;
        }
        if(!Pattern.compile(EMAIL_STRING).matcher(gmail.getText().toString().trim()).matches()){
            gmail.requestFocus();
            gmail.setError("Please enter Valid Email");
            return false;
        }

        return true;
    }

    private void verifyOTP(String gmail, String name) {
       otp = input1.getText().toString() + input2.getText().toString() + input3.getText().toString() + input4.getText().toString()+ input5.getText().toString()+ input6.getText().toString();
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        VerifySignUpOtpRequest request = new VerifySignUpOtpRequest(logid,otp,mobileno,name,gmail);
        Call<VerifyOtpResponse> call = serviceInterface.verifySingUpOTP(request);
        call.enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    String message = String.valueOf(response.body().getMessage());

                    String id = String.valueOf(response.body().getUser_id());
                    String email = String.valueOf(response.body().getUser_email());

                    if (status.equals("1")) {
                    //    Toast.makeText(CreateAcountActivity.this, message, Toast.LENGTH_SHORT).show();
                        getprofile(id);

                        //  getprofile(id);
                      /*  sharedpreferences = getSharedPreferences("Event_IT", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("UserId", id);
                        editor.putString("UserEmail", email);
                        editor.commit();


                        Toast.makeText(LogInActivity.this, " login  Succesfull", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                        intent.putExtra("afterLogin", 1);
                        startActivity(intent);

                       */
                        finish();
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


                        Toast.makeText(CreateAcountActivity.this, " Register  Succesfull", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CreateAcountActivity.this, MainActivity.class);
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


    private boolean validateMobile(String toString) {
        Pattern p = Pattern.compile("[0-9]{10}");
        Matcher m = p.matcher(toString);
        return m.matches();
    }

    /*validation of create an account*/


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