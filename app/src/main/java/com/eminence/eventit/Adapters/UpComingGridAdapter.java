package com.eminence.eventit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eminence.eventit.Activities.EventDetails;
import com.eminence.eventit.HomeActivity.EventAboutActivity;
import com.eminence.eventit.R;
import com.eminence.eventit.fragment.EventDetailsFragment;
import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventListData;
import com.eminence.eventit.utils.Constants;


import java.util.List;

public class UpComingGridAdapter extends RecyclerView.Adapter<UpComingGridAdapter.MyViewHolder> {
    Context context;

    public UpComingGridAdapter(Context context, List<UpcomingEventListData> arrayList) {
        this.context = context;
        ArrayList = arrayList;
    }

    List<UpcomingEventListData> ArrayList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.events_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTitle.setText(ArrayList.get(position).getAbout());
        holder.txtDescription.setText(ArrayList.get(position).getName());
        holder.txtPrice.setText(ArrayList.get(position).getPrice());
        holder.txtDate.setText(ArrayList.get(position).getDates());
        Glide.with(context).load(Constants.IMAGEURL+ArrayList.get(position).getMain_image())
                .placeholder(R.drawable.ic_profile_icon)
                .into(holder.imgEventPlace);
      String id = ArrayList.get(position).getId();
        holder.imgEventPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetails.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
      return ArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDescription,txtPrice,txtDate;
        ImageView imgEventPlace;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             txtTitle = itemView.findViewById(R.id.txtTitle);
             txtDescription = itemView.findViewById(R.id.txtDescription);
             txtPrice = itemView.findViewById(R.id.txtPrice);
             imgEventPlace = itemView.findViewById(R.id.imgEventPlace);
             txtDate = itemView.findViewById(R.id.date);
        }
    }
}
