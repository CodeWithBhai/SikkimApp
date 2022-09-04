package com.eminence.eventit.Activities.details.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.eminence.eventit.Activities.details.PlaceDetails
import com.eminence.eventit.Activities.details.models.Addon
import com.eminence.eventit.Activities.details.models.AddonX
import com.eminence.eventit.Partner.AddedBookingActivity
import com.eminence.eventit.R
import com.eminence.eventit.interfaces.AddOnListner

class AddOnEventService (private val context: Context,
                         private val discountList: List<AddonX>,
                         private val Cart_no: Int,
                         private val addOnListner: AddOnListner) : RecyclerView.Adapter<AddOnEventService.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.addon_services_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: AddonX = discountList[position]
        holder.txtAddOnServicePrice.text = categorylist.price
        holder.txtAddOnServiceName.text = categorylist.name
        if (categorylist.addoncart_id!=0){
            holder.txtAddOn.visibility = View.GONE
            holder.txtAfterAddOn.visibility = View.VISIBLE
        }
        holder.txtAddOn.setOnClickListener {
            if (Cart_no!=0){
            addOnListner.addOnListner(Cart_no.toString(),categorylist.id,"1",categorylist.price)

            } else{
                Toast.makeText(this.context,"Not allowed", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAddOnServiceName: TextView = itemView.findViewById(R.id.txtAddOnServiceName)
        val txtAddOnServicePrice: TextView = itemView.findViewById(R.id.txtAddOnServicePrice)
        val txtAddOn: TextView = itemView.findViewById(R.id.txtAddOn)
        val txtAfterAddOn:TextView = itemView.findViewById(R.id.txtAfterAddOn)

    }
}