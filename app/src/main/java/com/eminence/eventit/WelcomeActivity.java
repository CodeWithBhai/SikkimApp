package com.eminence.eventit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eminence.eventit.interfaces.ServiceInterface;
import com.eminence.eventit.ui.home.model.slider.SliderData;
import com.eminence.eventit.ui.home.model.slider.SliderResponse;
import com.eminence.eventit.utils.ApiClient;
import com.eminence.eventit.utils.Constants;
import com.eminence.eventit.utils.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private List<SliderData> sliderModalArrayList;
    private TextView[] dots;
    private TextView btnNext;
    private PreferenceManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        // Checking for first time launch - before calling setContentView()
        prefManager = new PreferenceManager(this);
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.SHARED_CART_ITEM, "0");
        editor.apply();

        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnNext = findViewById(R.id.btn_next);

        sliderModalArrayList = new ArrayList<>();

        getSliderData();

        changeStatusBarColor();

        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < sliderModalArrayList.size()) {
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
    }

    private void getSliderData() {
        sliderModalArrayList.clear();
        ServiceInterface serviceInterface = ApiClient.INSTANCE.getRetrofitUser().create(ServiceInterface.class);
        Call<SliderResponse> call = serviceInterface.getAppSliderList();
        call.enqueue(new Callback<SliderResponse>() {
            @Override
            public void onResponse(Call<SliderResponse> call, Response<SliderResponse> response) {
                if (response.isSuccessful()) {
                    String status = String.valueOf(response.body().getStatus());
                    sliderModalArrayList = response.body().getData();
                    if (status.equals("1")) {
                        setSlider(sliderModalArrayList);
                        addBottomDots(0);
                    }
                }
            }

            @Override
            public void onFailure(Call<SliderResponse> call, Throwable t) {
                Log.d("ff", t.toString());
            }
        });
    }

    private void setSlider(List<SliderData> sliderModalArrayList) {
        myViewPagerAdapter = new MyViewPagerAdapter(WelcomeActivity.this, sliderModalArrayList);
        viewPager.setAdapter(myViewPagerAdapter);
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[sliderModalArrayList.size()];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < sliderModalArrayList.size(); i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(50);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
               addBottomDots(position);
//            if (position == sliderModalArrayList.size() - 1) {
//               // btnNext.setText(getString(R.string.start));
//            } else {
//               // btnNext.setText(getString(R.string.next));
//            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {

        LayoutInflater layoutInflater;
        Context context;
        List<SliderData> sliderModalArrayList;

        public MyViewPagerAdapter(Context context, List<SliderData> sliderModalArrayList) {
            this.context = context;
            this.sliderModalArrayList = sliderModalArrayList;
        }

        @Override
        public int getCount() {
            return sliderModalArrayList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == (RelativeLayout) object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.entro_slider_row, container, false);
            ImageView imageView = view.findViewById(R.id.imgSlider);
            TextView titleTV = view.findViewById(R.id.txt_title);
            TextView headingTV = view.findViewById(R.id.txt_description);

            SliderData modal = sliderModalArrayList.get(position);
            titleTV.setText(modal.getTitle());
            headingTV.setText(modal.getDescription());

            Glide.with(context).load(Constants.IMAGEURL+modal.getImage())
                    .placeholder(R.drawable.acount_bg)
                    .circleCrop()
                    .into(imageView);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((RelativeLayout) object);
        }
    }


}