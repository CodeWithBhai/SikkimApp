package com.eminence.eventit.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.bold
import com.bumptech.glide.Glide
import com.eminence.eventit.Models.TimeSlotRequest
import com.eminence.eventit.Models.eventDetails.EventDetailsResponse
import com.eminence.eventit.R
import com.eminence.eventit.utils.ApiClient
import com.eminence.eventit.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetails : AppCompatActivity() {
    private var imageMainImage: ImageView? = null
    private var txtPlaceName: TextView? = null
    private var txtAbout: TextView? = null
    private var txtbestTime: TextView? = null
    private var txtName: TextView? = null
    private var txtPrice: TextView? = null
    private var headingAbout: TextView? = null
    private var txtHeadDoanddont: TextView? = null

    private var first = "";
    private var second = "";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        supportActionBar?.hide()
        val id =intent.getStringExtra("id")
        initView()
        getEventDetails(id)


    }
    private fun getEventDetails(id: String?) {
        ApiClient.instence_user?.getEventDetails(TimeSlotRequest(id.toString()))?.enqueue(object :
            Callback<EventDetailsResponse> {
            override fun onFailure(call: Call<EventDetailsResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<EventDetailsResponse>, response: Response<EventDetailsResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()?.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.status?.let {
                            if(it == 1) {

                                response.body()?.main_image?.let {
                                    imageMainImage?.let { it1 ->
                                        Glide.with(this@EventDetails).load(Constants.IMAGEURL+it)
                                            .placeholder(R.drawable.ic_profile_icon)
                                            .into(it1)
                                    }
                                }
                                response.body()?.place_name?.let {
                                    txtPlaceName?.text = it
                                    second = it
                                }
                                response.body()?.about?.let {
                                    txtAbout?.text = it
                                }
                                response.body()?.name?.let {
                                    txtName?.text = it
                                    first = it
                                }
                                headingAbout?.text = " About The " +first+ " At " +second

                                val feedetail = SpannableStringBuilder()
                                    .bold { append("The Entry Fee ") }
                                    .append("Per Person is ")
                                    .bold{ append("INR")}
                                    .bold{ append(" XXX")}
                                    .append(" and")
                                    .bold{ append(" Parking")}
                                    .append(" is")
                                    .bold{ append(" INR")}
                                    .bold{ append(" XXX")}
                                   txtPrice?.text = feedetail
                                val doAndDonthead = SpannableStringBuilder()
                                    .bold { append("The Following Do's & Dont''s Can Make Your Trip To ") }
                                    .bold { append(first) }
                                    .bold { append(" Enjoyable") }

                                txtHeadDoanddont?.text = doAndDonthead







                            }
                        }
                    } else {
                        Toast.makeText(this@EventDetails, ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@EventDetails, "Something is wrong try again later", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    private fun initView() {

        imageMainImage = findViewById(R.id.imageMainImage)
        txtPlaceName = findViewById<TextView>(R.id.txtPlaceName)
        txtAbout = findViewById<TextView>(R.id.txtAbout)
        txtName = findViewById(R.id.txtName)
        txtPrice = findViewById(R.id.txtPrice)
        txtHeadDoanddont = findViewById(R.id.txtHeadDoanddont)
        txtbestTime = findViewById<TextView>(R.id.txtbestTime)
        headingAbout = findViewById<TextView>(R.id.headingAbout).also { it.paint?.isUnderlineText =true }

    }

}