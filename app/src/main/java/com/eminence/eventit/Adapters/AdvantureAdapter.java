package com.eminence.eventit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eminence.eventit.Models.AdvantureModel;
import com.eminence.eventit.Models.ToDoModel;
import com.eminence.eventit.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdvantureAdapter extends RecyclerView.Adapter<AdvantureAdapter.viewHolder>{
    ArrayList<AdvantureModel> list;
    Context context;

    public AdvantureAdapter(ArrayList<AdvantureModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_adavanture_row, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        AdvantureModel model = list.get(position);
        holder.imgAdvanture.setImageResource(model.getImgAdvanture());
        holder.locationAdvanture.setText(model.getLocationAdvanture());
        holder.nameAdvanture.setText(model.getNameAdvanture());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgAdvanture;
        TextView locationAdvanture, nameAdvanture;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgAdvanture = itemView.findViewById(R.id.imgAdvanture);
            locationAdvanture = itemView.findViewById(R.id.locationAdvanture);
            nameAdvanture = itemView.findViewById(R.id.nameAdvanture);
        }
    }
}
