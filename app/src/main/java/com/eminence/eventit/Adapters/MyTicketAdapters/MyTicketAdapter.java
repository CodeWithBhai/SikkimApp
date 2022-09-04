package com.eminence.eventit.Adapters.MyTicketAdapters;

import static com.eminence.eventit.utils.Constants.IMAGEURL;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eminence.eventit.Accounts.TicketHistoryActivity;
import com.eminence.eventit.CartModels.CartListResponseData;
import com.eminence.eventit.Models.MyTicketModels.MyTicketResponseData;
import com.eminence.eventit.R;

import java.util.List;

public class MyTicketAdapter extends RecyclerView.Adapter<MyTicketAdapter.ViewHolder> {
    List<MyTicketResponseData> list;
    Context context;

    public MyTicketAdapter(List<MyTicketResponseData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyTicketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listView = layoutInflater.inflate(R.layout.simple_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketAdapter.ViewHolder holder, int position) {
        final MyTicketResponseData myTicketResponseData = list.get(position);
        holder.orderNumber.setText(myTicketResponseData.getOrder_unique_id());
        holder.allTicket.setText(myTicketResponseData.getTotal_tickets());
        holder.bookingDate.setText(myTicketResponseData.getCreated_at());
        holder.categoryName.setText(myTicketResponseData.getCategory_name());
        holder.locationName.setText(myTicketResponseData.getPlace_name());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TicketHistoryActivity.class);
              //  intent.putExtra("OrderNo", orderN);
                context.startActivity(intent);
            }
        });

        Glide.with(context).load(IMAGEURL +list.get(position).getPlace_image()).into(holder.orderImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        LinearLayout layout;
        TextView orderNumber, bookingDate, categoryName, locationName, allTicket;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            bookingDate = itemView.findViewById(R.id.bookingDate);
            categoryName = itemView.findViewById(R.id.categoryName);
            locationName = itemView.findViewById(R.id.locationName);
            allTicket = itemView.findViewById(R.id.allTicket);
            orderImage = itemView.findViewById(R.id.orderImage);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
