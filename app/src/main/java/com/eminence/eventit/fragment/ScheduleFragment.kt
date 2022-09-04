package com.eminence.eventit.fragment

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eminence.eventit.Accounts.LogInActivity
import com.eminence.eventit.Activities.details.PlaceDetails
import com.eminence.eventit.Adapters.TimeSlotAdapter
import com.eminence.eventit.Models.*
import com.eminence.eventit.Partner.AddedBookingActivity
import com.eminence.eventit.R
import com.eminence.eventit.interfaces.ItemClickListener
import com.eminence.eventit.utils.ApiClient
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val EVENTACTIVITYID = "eventActivityId"
private const val QUATITY = "quantity"
private const val PRICE = "price"
private const val DATES = "dates"
private const val BOOKINGTYPES = "bookingType"
private const val PLACEID = "PlaceId"
private const val CITY = "City"






class ScheduleFragment : BottomSheetDialogFragment(), ItemClickListener {
  //  var hashMap : HashMap<String, String> = HashMap<String, String> ()

    fun ScheduleFragment() {
        // Required empty public constructor
    }


    private var timeSlotRecyclerView: RecyclerView? = null
    private var timeSlotList: ArrayList<TimeSlotResponseListData> = ArrayList()
    var adapter: TimeSlotAdapter? = null

    private var eventActivityID: String? = null
    private var quatity: String? = null
    private var price: String? = null
    private var dates: String? = null
    private var bookingType: String? = null
    private var placeId: String? = null
    private var city: String? = null

    var userLogin:String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            eventActivityID = it.getString(EVENTACTIVITYID)
            quatity = it.getString(QUATITY)
            price = it.getString(PRICE)
            dates = it.getString(DATES)
            bookingType = it.getString(BOOKINGTYPES)
            placeId = it.getString(PLACEID)
            city = it.getString(CITY)
        }

     /*   hashMap["eventActivityID"] = eventActivityID.toString()
        hashMap["quantity"] = quatity.toString()
        hashMap["price"] = price.toString()
        hashMap["dates"] = dates.toString()
        hashMap["bookingType"] = bookingType.toString()

      */
    }

   companion object {
        @JvmStatic
        fun newInstance(eventActivityID: String, qua: String, price: String,dates:String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(EVENTACTIVITYID, eventActivityID)
                    putString(QUATITY, qua)
                    putString(PRICE, price)
                    putString(DATES, dates)
                    putString(BOOKINGTYPES,bookingType)

                }
            }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        initView(view)
        getTimeSlotData()
        val sharedPreferences = this.activity!!.getSharedPreferences("Event_IT", Context.MODE_PRIVATE)

         userLogin = sharedPreferences.getString("UserId", "")

        return view
    }

    private fun initView(view: View?) {
         timeSlotRecyclerView = view?.findViewById(R.id.timeSlotRecyclerView)
    }

    private fun getTimeSlotData() {
        ApiClient.instence_user?.getTimeSlot(TimeSlotRequest(eventActivityID.toString()))?.enqueue(object :
            Callback<TimeSlotResponse> {
            override fun onFailure(call: Call<TimeSlotResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<TimeSlotResponse>, response: Response<TimeSlotResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()?.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.status?.let {
                            if(it == 1) {
                                response.body()?.data?.let { banner ->
                                    if(banner.isNotEmpty()){
                                        timeSlotList.addAll(banner)
                                        bindTimeSlotList(timeSlotList)
                                    }
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

    private fun bindTimeSlotList(timeSlotList: ArrayList<TimeSlotResponseListData>) {
        timeSlotRecyclerView?.layoutManager = GridLayoutManager(requireContext(), 1)
        val adapter = TimeSlotAdapter(requireContext(), timeSlotList,this )
        timeSlotRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onClicks(name: String) {
        if (!userLogin.equals("")){
            addToCart(name)
        }
        else{
            Toast.makeText(requireContext(), "Kindly Login First ", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LogInActivity::class.java)
            startActivity(intent)
        }
      /*  timeSlotRecyclerView?.post(Runnable {
            adapter?.notifyDataSetChanged()
        })

       */
    }

    private fun addToCart(name: String) {
        ApiClient.instence_user?.addToCart(AddToCartRequest(userLogin.toString(),eventActivityID.toString(),quatity.toString(),price.toString(),dates.toString(),name))?.enqueue(object :
            Callback<AddToCartResponse>{
            override fun onFailure(call: Call<AddToCartResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<AddToCartResponse>,
                response: Response<AddToCartResponse>
            ) {
                if (response.isSuccessful){
                    val status = response.body()?.status.toString()
                    if (status.equals("1",true)) {
                        Toast.makeText(requireContext(), ""+response.body()?.message, Toast.LENGTH_SHORT).show()

                        dismissAllowingStateLoss()
                        val intent = Intent (getActivity(), PlaceDetails::class.java)
                        intent.putExtra("id",placeId)
                        intent.putExtra("city",city)
                        startActivity(intent)
                    }else{
                        Toast.makeText(requireContext(), ""+response.body()?.message, Toast.LENGTH_SHORT).show()

                    }
                }else{
                    Toast.makeText(requireContext(), " Something is wrong ", Toast.LENGTH_SHORT).show()

                }
            }
            })
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

}