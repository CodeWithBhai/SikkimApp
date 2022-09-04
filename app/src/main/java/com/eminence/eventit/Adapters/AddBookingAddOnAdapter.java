package com.eminence.eventit.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eminence.eventit.R;

public class AddBookingAddOnAdapter extends RecyclerView.Adapter<AddBookingAddOnAdapter.MyViewHolder> {
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.add_booking_add_on_row, parent, false);
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
        TextView snackTitle,placeName,txtPrice,minubutton,quantity,plusButton;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            snackTitle = itemView.findViewById(R.id.snackTitle);
            placeName = itemView.findViewById(R.id.placeName);
            txtPrice = itemView.findViewById(R.id.price);
            minubutton = itemView.findViewById(R.id.minubutton);
            quantity = itemView.findViewById(R.id.quantity);
            plusButton = itemView.findViewById(R.id.plusButton);
        }
    }
}
