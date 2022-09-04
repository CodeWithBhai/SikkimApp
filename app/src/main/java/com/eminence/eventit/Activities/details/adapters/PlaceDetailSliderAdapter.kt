package com.eminence.eventit.Activities.details.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.eminence.eventit.Accounts.SliderAdapter
import com.eminence.eventit.Activities.details.models.Banner
import com.eminence.eventit.R
import com.eminence.eventit.interfaces.BannerListner
import com.eminence.eventit.ui.home.model.banner.BannerListData
import com.eminence.eventit.utils.Constants
import com.smarteist.autoimageslider.SliderViewAdapter

class PlaceDetailSliderAdapter (val context: Context, private val bannerList: List<Banner>) : SliderViewAdapter<PlaceDetailSliderAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_image_slider, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.imageView.let {
            Glide.with(context).load( Constants.IMAGEURL+bannerList[position].image)
                .placeholder(R.drawable.banner)
                .into(it)
        }

    }

    override fun getCount(): Int {
        return bannerList.size
    }

    inner class Holder(itemView: View) : ViewHolder(itemView) {
        var imageView: ImageView
        init {
            imageView = itemView.findViewById(R.id.image_view)
        }
    }
}