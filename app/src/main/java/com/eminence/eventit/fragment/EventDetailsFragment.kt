package com.eminence.eventit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.eminence.eventit.Models.TimeSlotRequest
import com.eminence.eventit.Models.TimeSlotResponse
import com.eminence.eventit.Models.eventDetails.EventDetailsResponse
import com.eminence.eventit.R
import com.eminence.eventit.utils.ApiClient
import com.eminence.eventit.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailsFragment : BottomSheetDialogFragment() {

    private var imageMainImage: ImageView? = null
    private var txtPlaceName: TextView? = null
    private var txtAbout: TextView? = null
    private var txtName: TextView? = null

    fun EventDetailsFragment() {
        // Required empty public constructor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_event_details, container, false)

        initView(view)
        getEventDetails()

        return view


    }

    private fun getEventDetails() {
        ApiClient.instence_user?.getEventDetails(TimeSlotRequest("4"))?.enqueue(object :
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
                                                Glide.with(requireActivity()).load(Constants.IMAGEURL+it)
                                                    .placeholder(R.drawable.ic_profile_icon)
                                                    .into(it1)
                                            }
                                        }
                                response.body()?.place_name?.let {
                                     txtPlaceName?.text = it
                                }
                                response.body()?.about?.let {
                                    txtAbout?.text = it
                                }
                                response.body()?.name?.let {
                                    txtName?.text = it
                                }
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(), ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Something is wrong try again later", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initView(view: View?) {

        imageMainImage = view?.findViewById(R.id.imageMainImage)
        txtPlaceName = view?.findViewById(R.id.txtPlaceName)
        txtAbout = view?.findViewById(R.id.txtAbout)
        txtName = view?.findViewById(R.id.txtName)


    }


}