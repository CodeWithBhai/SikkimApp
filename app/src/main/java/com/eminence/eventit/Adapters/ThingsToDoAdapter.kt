package com.eminence.eventit.Adapters

import android.content.Context
import android.content.Intent
import com.eminence.eventit.Models.ToDoModel
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.eminence.eventit.R
import android.widget.TextView
import com.bumptech.glide.Glide
import com.eminence.eventit.Activities.details.PlaceDetails
import com.eminence.eventit.HomeActivity.EventAboutActivity
import com.eminence.eventit.interfaces.AddOnListner
import com.eminence.eventit.interfaces.ThingToDoListner
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedListData
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoListData
import com.eminence.eventit.utils.Constants
import java.util.ArrayList

class ThingsToDoAdapter (
        private val context: Context,
        private val discountList: List<ThingsToDoListData>,
        private  val thingToDoListner: ThingToDoListner) :
        RecyclerView.Adapter<ThingsToDoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.things_todo_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: ThingsToDoListData = discountList[position]
        holder.txtTitle.text = categorylist.name
        holder.imgPlace.let {
            Glide.with(context).load(Constants.IMAGEURL+categorylist.main_image)
                    .placeholder(R.drawable.ic_profile_icon)
                    .into(it)
        }

        holder.placeLayout.setOnClickListener{
            val intent = Intent(context, EventAboutActivity::class.java)
            intent.putExtra("id", categorylist.id)
           // intent.putExtra("category_name", categorylist.category_name)
            intent.putExtra("location", categorylist.location)
            intent.putExtra("about", categorylist.about)
            intent.putExtra("name", categorylist.name)
            intent.putExtra("main_image", categorylist.main_image)
            intent.putExtra("resId", R.drawable.ic_profile_icon)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle:TextView = itemView.findViewById(R.id.txtTitle)
        val imgPlace:ImageView = itemView.findViewById(R.id.imgPlace)
        val placeLayout: LinearLayout = itemView.findViewById(R.id.placeLayout)

    }
}