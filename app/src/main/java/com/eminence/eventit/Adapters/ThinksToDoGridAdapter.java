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
import com.eminence.eventit.Activities.details.PlaceDetails;
import com.eminence.eventit.R;
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoListData;
import com.eminence.eventit.utils.Constants;

import java.util.List;

public class ThinksToDoGridAdapter extends RecyclerView.Adapter<ThinksToDoGridAdapter.MyViewHolder> {
    Context context;

    public ThinksToDoGridAdapter(Context context, List<ThingsToDoListData> arrayList) {
        this.context = context;
        ArrayList = arrayList;
    }

    List<ThingsToDoListData> ArrayList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.to_do_place_visited_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTitle.setText(ArrayList.get(position).getName());
        Glide.with(context).load(Constants.IMAGEURL+ArrayList.get(position).getMain_image())
                .placeholder(R.drawable.ic_profile_icon)
                .into(holder.imgPlace);
        String id = ArrayList.get(position).getId();
        String city = ArrayList.get(position).getLocation();
        holder.imgPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, PlaceDetails.class);
                intent.putExtra("id",id );
                intent.putExtra("city", city);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return  ArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription,placeLayout;
        ImageView imgPlace;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtCategoryName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgPlace = itemView.findViewById(R.id.imgCategory);
        }
    }
}