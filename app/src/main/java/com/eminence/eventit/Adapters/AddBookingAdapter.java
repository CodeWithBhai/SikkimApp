package com.eminence.eventit.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eminence.eventit.R;

public class AddBookingAdapter extends RecyclerView.Adapter<AddBookingAdapter.MyViewHolder> {
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.add_booking_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDescription,txtPrice,minubutton,quantity,plusButton;
        ImageView image;
        LinearLayout addOnServices;
        RecyclerView addOnServicesRecyclerView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txteventActivityTitle);
            txtDescription = itemView.findViewById(R.id.txtname);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            image = itemView.findViewById(R.id.image);
            minubutton = itemView.findViewById(R.id.minubutton);
            quantity = itemView.findViewById(R.id.quantity);
            plusButton = itemView.findViewById(R.id.plusButton);
            addOnServices = itemView.findViewById(R.id.addOnServices);
            addOnServicesRecyclerView = itemView.findViewById(R.id.addOnServicesRecyclerView);

        }
    }
}
