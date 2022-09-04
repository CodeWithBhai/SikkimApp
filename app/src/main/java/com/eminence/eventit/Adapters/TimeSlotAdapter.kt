package com.eminence.eventit.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eminence.eventit.Activities.details.PlaceDetails
import com.eminence.eventit.Models.TimeSlotResponseListData
import com.eminence.eventit.R
import com.eminence.eventit.fragment.ScheduleFragment
import com.eminence.eventit.interfaces.ItemClickListener

class TimeSlotAdapter(
    private val context: Context,
    private val discountList: List<TimeSlotResponseListData>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>() {

    private var lastChecked: RadioButton? = null
    private var lastCheckedPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.service_time_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: TimeSlotResponseListData = discountList[position]
        holder.txtDate.text = categorylist.slot
        holder.radioButton.setChecked(false)
        holder.radioButton.setTag(position)

        if (position === 0 && holder.radioButton.isChecked) {
            lastChecked = holder.radioButton
            lastCheckedPos = 0
        }

        holder.radioButton.setOnClickListener { v ->
            val cb = v as RadioButton
            val clickedPos = (cb.tag as Int).toInt()
            if (cb.isChecked) {
                if (lastChecked != null) {
                    lastChecked?.setChecked(false)
                }
                lastChecked = cb
                lastCheckedPos = clickedPos
                val timeList: TimeSlotResponseListData = discountList[clickedPos]
               var time = timeList.slot
        //        hashmap["time_slot"] = time.toString()
                itemClickListener.onClicks(time)

              //  val placeDetails = PlaceDetails()
            //    placeDetails.setEventAddDetails(hashmap)




            } else lastChecked = null

        }

//        categorylist.addon.let { addOnList ->
//            if(addOnList.size != 0) {
//                holder.addOnServices.visibility = View.VISIBLE
//                holder.addOnServicesRecyclerView.layoutManager = GridLayoutManager(context, 1)
//                val adapter = AddOnServicesAdapter(context, categorylist.addon)
//                holder.addOnServicesRecyclerView.adapter = adapter
//                adapter.notifyDataSetChanged()
//            }
//        }

    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDate: TextView = itemView.findViewById(R.id.txtDate)
        val radioButton: RadioButton = itemView.findViewById(R.id.radioButton)

    }
}