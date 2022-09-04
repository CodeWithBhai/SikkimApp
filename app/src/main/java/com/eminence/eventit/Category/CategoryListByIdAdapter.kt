package com.eminence.eventit.Category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eminence.eventit.Activities.details.PlaceDetails
import com.eminence.eventit.Category.model.categoryListByCity.CategoryListByCityData
import com.eminence.eventit.R
import com.eminence.eventit.interfaces.CategoryListByCityListner
import com.eminence.eventit.utils.Constants

class CategoryListByIdAdapter (private val context: Context, private val discountList: List<CategoryListByIdData>, private  val categoryClickListner: CategoryListByCityListner) : RecyclerView.Adapter<CategoryListByIdAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.to_do_place_visited_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: CategoryListByIdData = discountList[position]
        holder.categoryName.text = categorylist.name

        holder.img_category.let {
            Glide.with(context).load(Constants.IMAGEURL+categorylist.main_image)
                .placeholder(R.drawable.ic_profile_icon)
                .into(it)
        }

        holder.categoryLayout.setOnClickListener(View.OnClickListener {
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
        val categoryName: TextView = itemView.findViewById(R.id.txtCategoryName)
        val img_category: ImageView = itemView.findViewById(R.id.imgCategory)
        val categoryLayout: LinearLayout = itemView.findViewById(R.id.categoryLayout)

    }
}