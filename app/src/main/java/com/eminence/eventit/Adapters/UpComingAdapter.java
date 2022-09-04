package com.eminence.eventit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eminence.eventit.Activities.UpComingActivity;
import com.eminence.eventit.BookingFragment.Upcoming_fragment;
import com.eminence.eventit.Models.UpComingModel;
import com.eminence.eventit.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.MyViewHolder> {
    Context context;
    List<UpComingModel> upComingModels;
    String from;

    public UpComingAdapter(Context context, ArrayList<UpComingModel> upComingModels, String from) {
        this.context = context;
        this.upComingModels = upComingModels;
        this.from = from;
    }

    public UpComingAdapter(ArrayList<UpComingModel> upComingModels) {
        this.upComingModels = upComingModels;
    }

    public UpComingAdapter(Context context, List<UpComingModel> upComingModels, String from) {
        this.context = context;
        this.upComingModels = upComingModels;
        this.from = from;
    }

    public UpComingAdapter(ArrayList<UpComingModel> arrayList, UpComingActivity upComingActivity) {

    }

    @NonNull
    @NotNull
    @Override
    public UpComingAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_design, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UpComingAdapter.MyViewHolder holder, int position) {
        UpComingModel model = upComingModels.get(position);
        holder.orderNumber.setText(model.getOrderNumber());
        holder.bookingDate.setText(model.getBookingDate());
        holder.categoryName.setText(model.getCategoryName());
        holder.locationName.setText(model.getLocationName());
        holder.date.setText(model.getDate());


    }

    @Override
    public int getItemCount() {
        return upComingModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber, bookingDate, categoryName, locationName, date;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            bookingDate = itemView.findViewById(R.id.bookingDate);
            categoryName = itemView.findViewById(R.id.categoryName);
            locationName = itemView.findViewById(R.id.locationName);
            date = itemView.findViewById(R.id.date);

        }
    }
}
