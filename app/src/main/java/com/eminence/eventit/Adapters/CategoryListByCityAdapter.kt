package com.eminence.eventit.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eminence.eventit.Category.CategoryListByIdData
import com.eminence.eventit.Category.model.categoryListByCity.CategoryListByCityData
import com.eminence.eventit.R
import com.eminence.eventit.interfaces.CategoryClickListner
import com.eminence.eventit.interfaces.CategoryListByCityListner
import com.eminence.eventit.utils.Constants
import java.util.ArrayList

class CategoryListByCityAdapter(private val context: Context, private val discountList: ArrayList<CategoryListByCityData>, private val categoryClickListner: CategoryListByCityListner) : RecyclerView.Adapter<CategoryListByCityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.to_do_place_visited_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: CategoryListByCityData = discountList[position]
        holder.categoryName.text = categorylist.category_name
        holder.img_category.let {
            Glide.with(context).load(Constants.IMAGEURL+categorylist.main_image)
                .placeholder(R.drawable.ic_profile_icon)
                .into(it)
        }

        holder.categoryLayout.setOnClickListener {
            categoryClickListner.categoryListner(categorylist.id,categorylist.category_name)
        }
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