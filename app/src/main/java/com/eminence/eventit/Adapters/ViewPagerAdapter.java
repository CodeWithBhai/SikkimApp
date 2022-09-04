package com.eminence.eventit.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.eminence.eventit.BookingFragment.AllBookingFragment;
import com.eminence.eventit.BookingFragment.Upcoming_fragment;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 1:
                return new AllBookingFragment();

            default:
                return new Upcoming_fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
