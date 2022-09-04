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
import com.eminence.eventit.R
import com.eminence.eventit.interfaces.CategoryClickListner
import com.eminence.eventit.ui.home.model.category.CategoryListData
import com.eminence.eventit.utils.Constants

class CategoryAdapter (private val context: Context, private val discountList: List<CategoryListData>, private  val categoryClickListner: CategoryClickListner) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.category_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: CategoryListData = discountList[position]
        holder.categoryName.text = categorylist.cat_name
        var s = categorylist.id
        holder.img_category.let {
            Glide.with(context).load(Constants.IMAGEURL+categorylist.cat_icon)
                .placeholder(R.drawable.ic_profile_icon)
                .into(it)
        }

        holder.categoryLayout.setOnClickListener {
            categoryClickListner.userCategoryListner(categorylist.id,categorylist.cat_name)
        }
    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName:TextView = itemView.findViewById(R.id.txtCategoryName)
        val img_category:ImageView = itemView.findViewById(R.id.imgCategory)
        val categoryLayout:LinearLayout = itemView.findViewById(R.id.categoryLayout)

    }

}