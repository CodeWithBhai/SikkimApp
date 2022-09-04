package com.eminence.eventit.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.eminence.eventit.Accounts.CreateAcountActivity;
import com.eminence.eventit.Accounts.LogInActivity;
import com.eminence.eventit.MainActivity;
import com.eminence.eventit.Partner.PartnerStaffActivity;
import com.eminence.eventit.Profiles.AboutUsActivity;
import com.eminence.eventit.Profiles.BookingHistoryActivity;
import com.eminence.eventit.Profiles.EditProfileActivity;
import com.eminence.eventit.Profiles.FeedbackActivity;
import com.eminence.eventit.Profiles.HelpSupportActivity;
import com.eminence.eventit.Profiles.MyLocationActivity;
import com.eminence.eventit.R;
import com.eminence.eventit.ui.notifications.NotificationsFragment;
import com.eminence.eventit.utils.PreferenceManager;

public class NotificationsFragment extends Fragment {
    LinearLayout helpSupply, sendFeedback, myLocation, aboutUs, patnerStaffLogin, bookingHistory,logOut,profileLayout;
    TextView editProfile,txtUserName,logInRegister;
    ImageView bookinghistorylock,bookinghistoryUnlock,myLocationLock,myLoctionUnlock,afterLoginImage,sendFeedbackLock,sendFeedbackUnLock,helpAndSupportLock,helpAndSupportUnLock,logOutLock,logOutUnLock;

    private NotificationsViewModel notificationsViewModel;

    //private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_notifications, container, false);
        bookinghistorylock = view.findViewById(R.id.bookinghistorylock);
        bookinghistoryUnlock = view.findViewById(R.id.bookinghistoryUnlock);
        myLocationLock = view.findViewById(R.id.myLocationLock);
        myLoctionUnlock = view.findViewById(R.id.myLoctionUnlock);

        sendFeedback = view.findViewById(R.id.sendFeedback);
        txtUserName = view.findViewById(R.id.txtUserName);
        aboutUs = view.findViewById(R.id.aboutUs);
        helpSupply = view.findViewById(R.id.helpSupply);
        myLocation = view.findViewById(R.id.myLocation);
        patnerStaffLogin = view.findViewById(R.id.patnerStaffLogin);
        bookingHistory = view.findViewById(R.id.bookingHistory);
      //  editProfile = view.findViewById(R.id.editProfile);
        sendFeedbackLock = view.findViewById(R.id.sendFeedbackLock);
        sendFeedbackUnLock = view.findViewById(R.id.sendFeedbackUnLock);
        helpAndSupportLock = view.findViewById(R.id.helpAndSupportLock);
        helpAndSupportUnLock = view.findViewById(R.id.helpAndSupportUnLock);
        logOutLock = view.findViewById(R.id.logOutLock);
        logOutUnLock = view.findViewById(R.id.logOutUnLock);
        logInRegister = view.findViewById(R.id.logInRegister);
        logOut = view.findViewById(R.id.logOut);
        afterLoginImage = view.findViewById(R.id.afterLoginImage);
        profileLayout = view.findViewById(R.id.profileLayout);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Event_IT", Context.MODE_PRIVATE);

        String userLogin  = sharedPreferences.getString("UserId", "");
        String userEmail  = sharedPreferences.getString("UserEmail", "");
        String UserName  = sharedPreferences.getString("UserName", "");


        if (!userLogin.equals("")){
          logInRegister.setVisibility(View.GONE);
          txtUserName.setText(UserName);
            bookinghistorylock.setVisibility(View.GONE);
            myLocationLock.setVisibility(View.GONE);
            sendFeedbackLock.setVisibility(View.GONE);
            helpAndSupportLock.setVisibility(View.GONE);
            logOutLock.setVisibility(View.GONE);
            bookinghistoryUnlock.setVisibility(View.VISIBLE);
            afterLoginImage.setVisibility(View.VISIBLE);
            myLoctionUnlock.setVisibility(View.VISIBLE);
            sendFeedbackUnLock.setVisibility(View.VISIBLE);
            helpAndSupportUnLock.setVisibility(View.VISIBLE);
            logOutUnLock.setVisibility(View.VISIBLE);

        }
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userLogin.equals("")){
                    Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Please Login or Sign Up First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bookingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userLogin.equals("")){

                    Intent intent = new Intent(getActivity(), BookingHistoryActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Please Login or Sign Up First", Toast.LENGTH_SHORT).show();
                }

            }
        });

        logInRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userLogin.equals("")){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    editor.commit();
                    Toast.makeText(getActivity(), "LogOut Succefully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getContext(), "Please Login or Sign Up First", Toast.LENGTH_SHORT).show();
                }



            }
        });

        patnerStaffLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PartnerStaffActivity.class);
                startActivity(intent);
            }
        });

        myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userLogin.equals("")){

                    Intent intent = new Intent(getActivity(), MyLocationActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Please Login or Sign Up First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        helpSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userLogin.equals("")){
                Intent intent = new Intent(getActivity(), HelpSupportActivity.class);
                startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Please Login or Sign Up First", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userLogin.equals("")){
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Please Login or Sign Up First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}