package com.eminence.eventit.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eminence.eventit.Accounts.GetOtpRequest;
import com.eminence.eventit.Accounts.GetOtpResponse;
import com.eminence.eventit.Accounts.LogInActivity;
import com.eminence.eventit.MainActivity;
import com.eminence.eventit.R;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    EditText editTextPersonName,editTextEmailid;
    TextView editTextPhone2,editEmailClick,TextEmailid,TextPersonName,editNameClick;
    Button updteProfileButton;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextEmailid = findViewById(R.id.editTextEmailid);
        updteProfileButton = findViewById(R.id.updteProfileButton);

        editTextPhone2 = findViewById(R.id.editTextPhone2);
        editEmailClick = findViewById(R.id.editEmailClick);
        TextEmailid = findViewById(R.id.TextEmailid);
        TextPersonName = findViewById(R.id.TextPersonName);
        editNameClick = findViewById(R.id.editNameClick);

        SharedPreferences sharedPreferences = getSharedPreferences("Event_IT", Context.MODE_PRIVATE);

        String userLogin  = sharedPreferences.getString("UserId", "");
        String userEmail  = sharedPreferences.getString("UserEmail", "");
        String UserName  = sharedPreferences.getString("UserName", "");
        String UserMobile  = sharedPreferences.getString("UserMobile", "");

                   editTextPhone2.setText(UserMobile);
                   TextEmailid.setText(userEmail);
                   TextPersonName.setText(UserName);

        editEmailClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextEmailid.setVisibility(View.GONE);
                editTextEmailid.setVisibility(View.VISIBLE);
                editTextEmailid.requestFocus();
           //     editTextEmailid.getFocusable();

            }
        });
        editNameClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextPersonName.setVisibility(View.GONE);
                editTextPersonName.setVisibility(View.VISIBLE);
                editTextPersonName.requestFocus();
                //     editTextEmailid.getFocusable();

            }
        });

        updteProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UpdatedName ;
                if (editTextPersonName.getText().toString().length()==0){
                    UpdatedName = UserName;
                }else{
                    UpdatedName =editTextPersonName.getText().toString();
                }
                String UpdatedEmail ;
                if (editTextEmailid.getText().toString().length()==0){
                    UpdatedEmail = userEmail;
                }else{
                    UpdatedEmail =editTextEmailid.getText().toString();
                }
                updateProfile(userLogin,UpdatedName,UpdatedEmail);
            }
        });
    }

    private void updateProfile(String userLogin, String updatedName, String updatedEmail) {
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        updateProfileRequest request = new updateProfileRequest(userLogin,updatedName,updatedEmail);
        Call<updateProfileResponse> call = serviceInterface.updateProfile(request);
        call.enqueue(new Callback<updateProfileResponse>() {
            @Override
            public void onResponse(Call<updateProfileResponse> call, Response<updateProfileResponse> response) {
                if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    if (status.equals("1")) {
                        sharedpreferences = getSharedPreferences("Event_IT", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("UserName", updatedName);
                        editor.putString("UserEmail", updatedEmail);
                        editor.commit();
                        Toast.makeText(EditProfileActivity.this, "Profile Updated Succesfull", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                        intent.putExtra("afterLogin", 1);
                        startActivity(intent);
                        finish();

                    }
                }
            }

            @Override
            public void onFailure(Call<updateProfileResponse> call, Throwable t) {

            }
        });
    }


    public void back(View view) {
        onBackPressed();
    }
}