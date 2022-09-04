package com.eminence.eventit.Adapters.MyOrders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eminence.eventit.Models.MyTicketModels.MyTicketResponseData;
import com.eminence.eventit.Models.OrderDetails.AddonXY;
import com.eminence.eventit.Models.OrderDetails.OrderDetailsResponseData;
import com.eminence.eventit.Models.OrderDetails.TicketsXY;
import com.eminence.eventit.R;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    Context context;
    List<OrderDetailsResponseData> ArrayList;
    List<AddonXY> AddOnList;
    List<TicketsXY> TicketList;

    public MyOrderAdapter(Context context, List<OrderDetailsResponseData> arrayList) {
        this.context = context;
        ArrayList = arrayList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listView = layoutInflater.inflate(R.layout.ticket_qr_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {
        holder.eventName.setText(ArrayList.get(position).getActivity_event_name());
        holder.eventQty.setText(ArrayList.get(position).getQty()+" X ");
        AddOnList = ArrayList.get(position).getAddon();
        TicketList = ArrayList.get(position).getTickets();
        if (TicketList.size()!=0){
            Toast.makeText(context, "QR Code Generate", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ticketQRImg;
        LinearLayout add_on_service_visible;
        RecyclerView add_service, qrcodeListRecyclerView;
        TextView eventName, eventQty, addOnName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.eventName);
            eventQty = itemView.findViewById(R.id.eventQty);
            eventName = itemView.findViewById(R.id.eventName);
            add_on_service_visible = itemView.findViewById(R.id.add_on_service_visible);
            add_service = itemView.findViewById(R.id.add_service);
            qrcodeListRecyclerView = itemView.findViewById(R.id.qrcodeListRecyclerView);

        }
    }
}
