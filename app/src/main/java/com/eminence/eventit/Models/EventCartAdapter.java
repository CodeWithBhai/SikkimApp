package com.eminence.eventit.Models;

import static com.eminence.eventit.utils.Constants.IMAGEURL;

import android.annotation.SuppressLint;
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

import com.bumptech.glide.Glide;
import com.eminence.eventit.Activities.details.models.AddonX;
import com.eminence.eventit.CartModels.CartListResponseData;
import com.eminence.eventit.CartModels.Getcartamt;
import com.eminence.eventit.Partner.AddedBookingActivity;
import com.eminence.eventit.R;

import java.util.List;

public class EventCartAdapter extends RecyclerView.Adapter<EventCartAdapter.ViewHolder> {

    List<CartListResponseData> list;
    Context context;
    Getcartamt getcartamt;

    public EventCartAdapter(List<CartListResponseData> list, Context context, Getcartamt getcartamt) {
        this.list = list;
        this.context = context;
        this.getcartamt = getcartamt;
    }

    @NonNull
    @Override
    public EventCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listView = layoutInflater.inflate(R.layout.add_booking_add_on_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventCartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final CartListResponseData cartListResponseData = list.get(position);
        holder.snackTitle.setText(cartListResponseData.getEvent_name());
        holder.price.setText(cartListResponseData.getPrice());
        holder.placeName.setText(cartListResponseData.getPlace_name());
        holder.txt_itemCount.setText(cartListResponseData.getQuantity());
       // holder.txtLocation.setText(cartListResponseData.getPlace_name());
      //  holder.txtPrice.setText(cartListResponseData.getPrice());
       // holder.txtName.setText(cartListResponseData.getEvent_name());

       // Glide.with(context).load(IMAGEURL +list.get(position).getEvent_image()).into(holder.imgActivity);

        int total_amt = Integer.parseInt(cartListResponseData.getPrice())* Integer.parseInt(cartListResponseData.getQuantity());

        holder.txt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qtr = holder.txt_itemCount.getText().toString();
                String fees = cartListResponseData.getPrice();
                if (!qtr.equals("15")){
                    int qty2 = Integer.parseInt(qtr)+1;
                    holder.txt_itemCount.setText(""+qty2);
                    updateqty(cartListResponseData.getAddon(), holder.txt_itemCount.getText().toString(), "plus");
                    holder.price.setText("" + total_amt);
                }
            }
        });

        holder.txt_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qtr = holder.txt_itemCount.getText().toString();
                String fees = cartListResponseData.getPrice();

                    int qty2 = Integer.parseInt(qtr)-1;
                    holder.txt_itemCount.setText(""+qty2);

                    if (qty2==0){
                        removeAt(position);
                        removeCart(cartListResponseData.getQuantity());
                    }
            }
        });

    }

    private void removeCart(String quantity) {

    }


    private void updateqty(List<AddonX> addon, String toString, String plus) {
        Toast.makeText(context, ""+addon, Toast.LENGTH_SHORT).show();
    }

    private void removeAt(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView snackTitle, placeName, price, txt_minus, txt_itemCount, txt_plus, txtLocation, txtPrice, txtName;
        LinearLayout lightSound;
        ImageView imgActivity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            snackTitle = itemView.findViewById(R.id.snackTitle);
            placeName = itemView.findViewById(R.id.placeName);
            price = itemView.findViewById(R.id.price);
            txt_itemCount = itemView.findViewById(R.id.txt_itemCount);
            txt_minus = itemView.findViewById(R.id.txt_minus);
            txt_plus = itemView.findViewById(R.id.txt_plus);
            lightSound = itemView.findViewById(R.id.lightSound);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgActivity = itemView.findViewById(R.id.imgActivity);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
