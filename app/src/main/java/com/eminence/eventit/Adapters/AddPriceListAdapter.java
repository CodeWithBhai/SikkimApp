package com.eminence.eventit.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eminence.eventit.R;

public class AddPriceListAdapter extends RecyclerView.Adapter<AddPriceListAdapter.MyViewHolder> {
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.add_booking_pricelist_row, parent, false);
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
        TextView evetnTitle,eventTotalPrice,eventPersonNumber,snackTitle,snackPersonNumber,snackTotalPrice;
        LinearLayout snackLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            snackLayout = itemView.findViewById(R.id.snackLayout);
            evetnTitle = itemView.findViewById(R.id.evetnTitle);
            eventTotalPrice = itemView.findViewById(R.id.eventTotalPrice);
            eventPersonNumber = itemView.findViewById(R.id.eventPersonNumber);
            snackTitle = itemView.findViewById(R.id.snackTitle);
            snackPersonNumber = itemView.findViewById(R.id.snackPersonNumber);
            snackTotalPrice = itemView.findViewById(R.id.snackTotalPrice);
        }
    }
}
