package com.eminence.eventit.Activities.details.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eminence.eventit.Activities.details.models.Activity
import com.eminence.eventit.R
import com.eminence.eventit.interfaces.AddOnListner
import com.eminence.eventit.interfaces.AddToBookingListner
import com.eminence.eventit.interfaces.EventdetailsListner
import com.eminence.eventit.utils.Constants

class ActivityAdapter  (private val context: Context, private val discountList: List<Activity>, private val timeScheduleListner: EventdetailsListner,private  val addToBookingListner: AddToBookingListner, private val addOnListner: AddOnListner) : RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.activity_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: Activity = discountList[position]
        holder.txtName.text = categorylist.name
        holder.txtLocation.text = categorylist.location
        holder.txtPrice.text = categorylist.price
        holder.imgActivity.let {
            Glide.with(context).load(Constants.IMAGEURL+categorylist.main_image)
                .placeholder(R.drawable.ic_profile_icon)
                .into(it)
        }

        holder.imgActivity.setOnClickListener {
            timeScheduleListner.eventListner("")
        }
       var type = "Activity"
        if (categorylist.cart_id!=0){
            holder.txtAddToBooking.visibility = View.GONE
            holder.txtAfterBooking.visibility = View.VISIBLE
        }
        holder.txtAddToBooking.setOnClickListener {
            addToBookingListner.addToBookListner(categorylist.id,"1", categorylist.price,categorylist.dates,type)


        }

        categorylist.addon.let { addOnList ->
            if(addOnList.size != 0) {
                holder.addOnServices.visibility = View.VISIBLE
                holder.addOnServicesRecyclerView.layoutManager = GridLayoutManager(context, 1)
                val adapter = AddOnServicesAdapter(context, categorylist.addon,categorylist.cart_id, addOnListner)
                holder.addOnServicesRecyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtLocation: TextView = itemView.findViewById(R.id.txtLocation)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        val txtAddToBooking: TextView = itemView.findViewById(R.id.txtAddToBooking)
        val txtAfterBooking: TextView = itemView.findViewById(R.id.txtAfterBooking)
        val imgActivity: ImageView = itemView.findViewById(R.id.imgActivity)
        val addOnServices: LinearLayout = itemView.findViewById(R.id.addOnServices)
        val addOnServicesRecyclerView: RecyclerView = itemView.findViewById(R.id.addOnServicesRecyclerView)

    }
}