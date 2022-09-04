package com.eminence.eventit.Adapters.MyOrders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eminence.eventit.Models.OrderDetails.TicketsXY;
import com.eminence.eventit.R;

import java.util.List;

public class QRCodeGenerateAdapter extends RecyclerView.Adapter<QRCodeGenerateAdapter.ViewHolder> {
    Context context;
    List<TicketsXY> TicketList;

    public QRCodeGenerateAdapter(Context context, List<TicketsXY> ticketList) {
        this.context = context;
        TicketList = ticketList;
    }

    @NonNull
    @Override
    public QRCodeGenerateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listView = layoutInflater.inflate(R.layout.qr_code_generate, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QRCodeGenerateAdapter.ViewHolder holder, int position) {
        holder.date_slotQR.setText(TicketList.get(position).getDate_slot());
        holder.time_slotQR.setText(TicketList.get(position).getTime_slot());
       // holder.ticketQRId.setText(TicketList.get(position).getEvent_activity_id());
        holder.actEv.setText(TicketList.get(position).getActivity_event_name());
        String Ticket_id = TicketList.get(position).getTicket_number();
        holder.ticketQRId.setText(Ticket_id);

        int ticketStatus = Integer.parseInt(TicketList.get(position).getStatus());
        if (ticketStatus!=0){
            Toast.makeText(context, "welcome", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return TicketList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ticketQRId, date_slotQR, time_slotQR, actEv;
        ImageView ticketQRImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ticketQRId = itemView.findViewById(R.id.ticketQRId);
            date_slotQR = itemView.findViewById(R.id.date_slotQR);
            time_slotQR = itemView.findViewById(R.id.time_slotQR);
            actEv = itemView.findViewById(R.id.actEv);
            ticketQRImg = itemView.findViewById(R.id.ticketQRImg);
        }
    }
}
