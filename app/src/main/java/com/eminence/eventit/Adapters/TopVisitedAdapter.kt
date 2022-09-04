package com.eminence.eventit.Adapters

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.eminence.eventit.R
import android.widget.TextView
import com.bumptech.glide.Glide
import com.eminence.eventit.Activities.SeeAllTopVisitors
import com.eminence.eventit.Activities.details.PlaceDetails
import com.eminence.eventit.HomeActivity.EventAboutActivity
import com.eminence.eventit.interfaces.TopPlaceClickListner
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedListData
import com.eminence.eventit.utils.Constants

class TopVisitedAdapter(private val context: Context, private val discountList: List<TopVisitedListData>, private  val topPlaceResponse: TopPlaceClickListner) : RecyclerView.Adapter<TopVisitedAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.top_visited_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: TopVisitedListData = discountList[position]
        holder.txtTitle.text = categorylist.name
        holder.txtDescription.text = categorylist.category_name
        holder.imgPlace.let {
            Glide.with(context).load(Constants.IMAGEURL+categorylist.main_image)
                .placeholder(R.drawable.ic_profile_icon)
                .into(it)
        }

        holder.placeLayout.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, PlaceDetails::class.java)
            intent.putExtra("id",categorylist.id)
            intent.putExtra("city",categorylist.location)

            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle:TextView = itemView.findViewById(R.id.txtTitle)
        val txtDescription:TextView = itemView.findViewById(R.id.txtDescription)
        val imgPlace:ImageView = itemView.findViewById(R.id.imgPlace)
        val placeLayout: LinearLayout = itemView.findViewById(R.id.placeLayout)

    }
}