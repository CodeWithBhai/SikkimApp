package com.eminence.eventit.Adapters

import android.content.Context
import android.content.Intent
import com.eminence.eventit.Models.EventModel
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.eminence.eventit.R
import android.widget.TextView
import com.bumptech.glide.Glide
import com.eminence.eventit.Activities.EventDetails
import com.eminence.eventit.Activities.details.PlaceDetails
import com.eminence.eventit.HomeActivity.EventAboutActivity
import com.eminence.eventit.interfaces.TopPlaceClickListner
import com.eminence.eventit.interfaces.UpComingEventListner
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedListData
import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventListData
import com.eminence.eventit.utils.Constants

class UpComingEventAdapter (private val context: Context, private val discountList: List<UpcomingEventListData>, private  val upcomingEventListner: UpComingEventListner) : RecyclerView.Adapter<UpComingEventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.events_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: UpcomingEventListData = discountList[position]
        holder.txtTitle.text = categorylist.about
        holder.txtDescription.text = categorylist.name
        holder.txtPrice.text = categorylist.price
        holder.txtDate.text = categorylist.dates
        holder.imgEventPlace.let {
            Glide.with(context).load(Constants.IMAGEURL+categorylist.main_image)
                .placeholder(R.drawable.ic_profile_icon)
                .into(it)
        }
           val  id = categorylist.id
        holder.upcomingEventLayout.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, EventDetails::class.java)
            intent.putExtra("id",id)
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle:TextView = itemView.findViewById(R.id.txtTitle)
        val txtDescription:TextView = itemView.findViewById(R.id.txtDescription)
        val txtPrice:TextView = itemView.findViewById(R.id.txtPrice)
        val txtDate:TextView = itemView.findViewById(R.id.date)
        val imgEventPlace:ImageView = itemView.findViewById(R.id.imgEventPlace)
        val upcomingEventLayout: LinearLayout = itemView.findViewById(R.id.upcomingEventLayout)

    }
}