package com.eminence.eventit.Activities.details.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eminence.eventit.Activities.details.models.Event
import com.eminence.eventit.R
import com.eminence.eventit.interfaces.AddOnListner
import com.eminence.eventit.interfaces.AddToBookingListner
import com.eminence.eventit.interfaces.EventdetailsListner

class EventAdapter (private val context: Context, private val discountList: List<Event>, private val timeScheduleListner: EventdetailsListner, private  val addToBookingListner: AddToBookingListner, private val addOnListner: AddOnListner) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.event_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: Event = discountList[position]
        holder.txtName.text = categorylist.name
        holder.txtLocation.text = categorylist.location
        holder.txtPrice.text = categorylist.price

//        holder.imgActivity.setOnClickListener {
//            timeScheduleListner.eventListner("")
//        }
        var type = "Event"
        if (categorylist.cart_id!=0){
            holder.txtAddToBooking.visibility = View.GONE
            holder.txtAfterBooking.visibility = View.VISIBLE
        }
        holder.txtAddToBooking.setOnClickListener {
            addToBookingListner.addToBookListner(categorylist.id,"1",categorylist.price,categorylist.dates,type)

        }


        categorylist.addon.let { addOnList ->
            if(addOnList.size != 0) {
                holder.addOnServices.visibility = View.VISIBLE
                holder.addOnServicesRecyclerView.layoutManager = GridLayoutManager(context, 1)
                val adapter = AddOnEventService(context, categorylist.addon,categorylist.cart_id,addOnListner)
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
        val txtAfterBooking: TextView = itemView.findViewById(R.id.txtAfterBooking)
        val txtAddToBooking: TextView = itemView.findViewById(R.id.txtAddToBooking)
        val addOnServices: LinearLayout = itemView.findViewById(R.id.addOnServices)
        val addOnServicesRecyclerView: RecyclerView = itemView.findViewById(R.id.addOnServicesRecyclerView)

    }
}