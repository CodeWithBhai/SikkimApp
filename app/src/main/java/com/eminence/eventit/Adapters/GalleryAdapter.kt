package com.eminence.eventit.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eminence.eventit.Activities.details.models.Gallery
import com.eminence.eventit.R
import com.eminence.eventit.utils.Constants

class GalleryAdapter (private val context: Context, private val discountList: List<Gallery>) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.gallery_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categorylist: Gallery = discountList[position]
        holder.imgGallery.let {
            Glide.with(context).load(Constants.IMAGEURL+categorylist.image)
                .placeholder(R.drawable.ic_profile_icon)
                .into(it)
        }
    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgGallery: ImageView = itemView.findViewById(R.id.imgGallery)

    }
}