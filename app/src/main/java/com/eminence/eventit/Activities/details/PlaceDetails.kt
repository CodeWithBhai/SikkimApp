package com.eminence.eventit.Activities.details

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eminence.eventit.Activities.details.adapters.ActivityAdapter
import com.eminence.eventit.Activities.details.adapters.EventAdapter
import com.eminence.eventit.Activities.details.adapters.PlaceDetailSliderAdapter
import com.eminence.eventit.Activities.details.models.*
import com.eminence.eventit.Activities.details.models.Gallery
import com.eminence.eventit.Activities.details.models.addOnServices.AddOnServicesRequest
import com.eminence.eventit.Activities.details.models.addOnServices.AddOnServicesResponse
import com.eminence.eventit.Adapters.GalleryAdapter
import com.eminence.eventit.Partner.AddedBookingActivity
import com.eminence.eventit.R
import com.eminence.eventit.fragment.EventDetailsFragment
import com.eminence.eventit.fragment.FilterFragment
import com.eminence.eventit.fragment.ScheduleFragment
import com.eminence.eventit.interfaces.AddOnListner
import com.eminence.eventit.interfaces.AddToBookingListner
import com.eminence.eventit.interfaces.EventdetailsListner
import com.eminence.eventit.utils.ApiClient
import com.eminence.eventit.utils.Constants
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PlaceDetails : AppCompatActivity() , EventdetailsListner , AddToBookingListner, AddOnListner {


    var goToBooking: LinearLayout? = null
    private var galleryRecyclerView: RecyclerView? = null
    private var activityRecyclerView: RecyclerView? = null
    private var eventRecyclerView: RecyclerView? = null
    private var imageMainImage: ImageView? = null

    private var txtPlaceName: TextView? = null
    var PlacedetailsHasmap : HashMap<String, String> = HashMap<String, String> ()

    private var txtAbout: TextView? = null
    private  var txtLocation: TextView? = null
    private  var upcomingEvent: TextView? = null
    private  var feesActivities: TextView? = null
    private   var totalPrice: TextView?= null
    private  var layoutsliderfilter: LinearLayout? = null
    private var goToBookingLayout: RelativeLayout? = null
    private  var filter: LinearLayout? = null


    var sliderView: SliderView? = null

    private var bannerList: ArrayList<Banner> = ArrayList()
    private var galleryList: ArrayList<Gallery> = ArrayList()
    private var activityList: ArrayList<Activity> = ArrayList()
    private var eventList: ArrayList<Event> = ArrayList()
    var userLogin:String?="0"
    var id:String? = "0"
    var city:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_phase_about)
        supportActionBar?.hide()

        initView()
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("Event_IT", MODE_PRIVATE)

         userLogin = sharedPreferences.getString("UserId", "")
        if (userLogin.equals("")){
            userLogin = "0"
        }
         id =intent.getStringExtra("id")
         city =intent.getStringExtra("city")
        getPlaceDetails(city,id)
        totalPrice()





        goToBooking?.setOnClickListener{
            val intent = Intent(applicationContext, AddedBookingActivity::class.java)
            startActivity(intent)
        }

        filter?.setOnClickListener {
            filterDialog()
        }

    }

    private fun filterDialog() {
        val frag = FilterFragment()
        frag.show(supportFragmentManager, "TAG")
    }

    private fun initView() {
        goToBooking = findViewById(R.id.goToBooking)
        galleryRecyclerView = findViewById(R.id.galleryRecyclerView)
        activityRecyclerView = findViewById(R.id.activityRecyclerView)
        eventRecyclerView = findViewById(R.id.eventRecyclerView)
        imageMainImage = findViewById(R.id.imageMainImage)
        txtPlaceName = findViewById(R.id.txtPlaceName)
        txtAbout = findViewById(R.id.txtAbout)
        sliderView = findViewById(R.id.sliderView)
        txtLocation = findViewById(R.id.txtLocation)
        upcomingEvent = findViewById(R.id.upcomingEvent)
        feesActivities = findViewById(R.id.feesActivities)
        layoutsliderfilter = findViewById(R.id.layoutsliderfilter)
        goToBookingLayout = findViewById(R.id.goToBookingLayout)
        filter = findViewById(R.id.filter)
        totalPrice = findViewById(R.id.totalPrice)



    }

    private fun getPlaceDetails(city: String?, id: String?) {
        ApiClient.instence_user?.getPlaceDetails(PlaceDetailsRequest(userLogin, id.toString(), city.toString()))?.enqueue(object :
            Callback<PlaceDetailsResponse> {
            override fun onFailure(call: Call<PlaceDetailsResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<PlaceDetailsResponse>, response: Response<PlaceDetailsResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()?.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.status?.let {
                            if(it == 1) {

                                imageMainImage?.let {
                                    Glide.with(this@PlaceDetails).load(Constants.IMAGEURL+response.body()?.main_image)
                                        .placeholder(R.drawable.ic_profile_icon)
                                        .into(it)
                                }

                                response.body()?.name?.let {
                                    txtPlaceName?.text = it
                                }
                                response.body()?.location?.let {
                                    txtLocation?.text = it
                                }

                                response.body()?.about?.let {
                                    txtAbout?.text = it
                                }

                                response.body()?.banners?.let { banner ->
                                    if(banner.isNotEmpty()){
                                        bannerList.addAll(banner)
                                        bindBannerList(bannerList)
                                    }
                                }

                                response.body()?.gallery?.let { gallery ->
                                    if(gallery.isNotEmpty()){
                                        galleryList.addAll(gallery)
                                        bindGalleryList(galleryList)
                                    }
                                }

                                response.body()?.activity?.let { activity ->
                                    if(activity.isNotEmpty()){
                                        feesActivities?.visibility = View.VISIBLE
                                        layoutsliderfilter?.visibility = View.VISIBLE
                                        activityList.addAll(activity)
                                        bindActivityList(activityList)
                                    }
                                }

                                response.body()?.event?.let { event ->
                                    if(event.isNotEmpty()){
                                        upcomingEvent?.visibility = View.VISIBLE
                                        layoutsliderfilter?.visibility = View.VISIBLE
                                        eventList.addAll(event)
                                        bindEventList(eventList)
                                    }
                                }
                            }
                        }
                    } else {
                        Toast.makeText(this@PlaceDetails, ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@PlaceDetails, "Something is wrong try again later", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun bindEventList(eventList: ArrayList<Event>) {
        eventRecyclerView?.layoutManager = GridLayoutManager(this@PlaceDetails, 1)
        val adapter = EventAdapter(this@PlaceDetails, eventList,this,this,this)
        eventRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun bindActivityList(activityList: ArrayList<Activity>) {
        activityRecyclerView?.layoutManager = GridLayoutManager(this@PlaceDetails, 1)
        val adapter = ActivityAdapter(this@PlaceDetails, activityList,this,this,this)
        activityRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun bindBannerList(bannerList: ArrayList<Banner>) {
        val sliderAdapter = PlaceDetailSliderAdapter(this,bannerList)
        sliderView?.setSliderAdapter(sliderAdapter)
        sliderView?.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderView?.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView?.startAutoCycle()
    }

    private fun bindGalleryList(galleryList: ArrayList<Gallery>) {
        galleryRecyclerView?.layoutManager = GridLayoutManager(this@PlaceDetails, 3)
        val adapter = GalleryAdapter(this@PlaceDetails, galleryList)
        galleryRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun back(view: View?) {
        onBackPressed()
    }
    public fun  setEventAddDetails(hasmap: HashMap<String, String>){

        var s = PlacedetailsHasmap
            PlacedetailsHasmap  = hasmap
    }

    override fun eventListner(name: String) {
        val frag = EventDetailsFragment()
        frag.show(supportFragmentManager, "TAG")
    }

    override fun addToBookListner(event_activity_id: String?, quantity: String?, price:String?, date:String?, bookingType:String) {
        val frag = ScheduleFragment()
        val bundle = Bundle()
        bundle.putString("eventActivityId", event_activity_id)
        bundle.putString("quantity", quantity.toString())
        bundle.putString("price", price)
        bundle.putString("dates", date)
        bundle.putString("bookingType",bookingType)
        bundle.putString("PlaceId",id)
        bundle.putString("City",city)


        frag.arguments = bundle
        frag.show(supportFragmentManager, "TAG")

       }

    override fun addOnListner(cart_id: String?, addon_id: String?, qty: String?, price:String?) {
        addOnServices(cart_id,addon_id,qty,price)
    }

    private fun addOnServices(cart_id: String?, addonId: String?, qty: String?, price: String?) {
        ApiClient.instence_user?.addOnServices(AddOnServicesRequest(cart_id,addonId,qty,price))?.enqueue(object :
            Callback<AddOnServicesResponse> {
            override fun onFailure(call: Call<AddOnServicesResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<AddOnServicesResponse>, response: Response<AddOnServicesResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()?.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.status?.let {
                            if(it == 1) {
                                Toast.makeText(this@PlaceDetails,""+response.body()?.message,Toast.LENGTH_SHORT).show()
                                val intent = intent
                                finish()
                                startActivity(intent)
                            }
                        }
                    } else {
                        Toast.makeText(this@PlaceDetails, ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@PlaceDetails, ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun totalPrice(){
        ApiClient.instence_user?.getPlaceDetailsTotal(PlaceDetailTotalRequest(userLogin.toString()))?.enqueue(object :
        Callback<PlaceDetailTotalResponse>{
            override fun onResponse(
                call: Call<PlaceDetailTotalResponse>,
                response: Response<PlaceDetailTotalResponse>) {
                if (response.isSuccessful) {
                  if (response.body()?.status.equals("1")){
                    goToBookingLayout?.visibility = View.VISIBLE
                    totalPrice?.text = response.body()?.total_amount
                }}

            }

            override fun onFailure(call: Call<PlaceDetailTotalResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
