package com.eminence.eventit.ui.home

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import com.smarteist.autoimageslider.SliderView
import com.eminence.eventit.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.eminence.eventit.Accounts.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eminence.eventit.NotificationActivity
import com.eminence.eventit.Activities.SeeAllTopVisitors
import com.eminence.eventit.Activities.SeeAllThinkToDo
import com.eminence.eventit.Activities.details.PlaceDetails
import com.eminence.eventit.SearchActivity
import com.eminence.eventit.Profiles.MyLocationActivity
import com.eminence.eventit.Activities.UpComingEventActivity
import com.eminence.eventit.Adapters.*
import com.eminence.eventit.AllCategory.MusicCategory
import com.eminence.eventit.Category.CategoryName
import com.eminence.eventit.interfaces.*
import com.eminence.eventit.ui.home.model.banner.BannerListData
import com.eminence.eventit.ui.home.model.banner.BannerResponse
import com.eminence.eventit.ui.home.model.category.CategoryListData
import com.eminence.eventit.ui.home.model.category.CategoryListResponse
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedListData
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedRequest
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedResponse
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoListData
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoRequest
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoResponse
import com.eminence.eventit.ui.home.model.upcoming.UpComingEventRequest
import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventListData
import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventResponse
import com.eminence.eventit.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class HomeFragment : Fragment() , BannerListner, CategoryClickListner , TopPlaceClickListner, ThingToDoListner,UpComingEventListner {

    var notifications: ImageView? = null
    var placeLayout: ImageView? = null
    var placeFull: CardView? = null
    private var recyclerVieww: RecyclerView? = null
    private var categoryRecyclerView: RecyclerView? = null
    private var topVisitedRecyclerView: RecyclerView? = null
    private var thingsToDoRecyclerView: RecyclerView? = null
    private var upComingEventRecyclerView: RecyclerView? = null
    var categoryMusic: LinearLayout? = null
    var event_about: LinearLayout? = null
    var filter: LinearLayout? = null
    var sellAllPlaces: LinearLayout? = null
    var sellAllTop: LinearLayout? = null
    var SeeAllToDo: LinearLayout? = null
    var SeeAllUpComing: LinearLayout? = null


    var myLocation: TextView? = null
    var filter1: TextView? = null
    var filter2: TextView? = null
    var filter3: TextView? = null
    var moreAbout: ImageView? = null
    var searchActivity: ImageView? = null
    var sliderView: SliderView? = null

    private var bannerList: ArrayList<BannerListData> = ArrayList()
    private var categoryList: ArrayList<CategoryListData> = ArrayList()
    private var topVisitedList: ArrayList<TopVisitedListData> = ArrayList()
    private var thingsToDoList: ArrayList<ThingsToDoListData> = ArrayList()
    private var upComingEventList: ArrayList<UpcomingEventListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initView(view)
        getData()

        notifications?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, NotificationActivity::class.java)
            startActivity(intent)
        })

        sellAllPlaces?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, SeeAllTopVisitors::class.java)
            startActivity(intent)
        })

        SeeAllToDo?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, SeeAllThinkToDo::class.java)
            startActivity(intent)
        })

        placeLayout?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, PlaceDetails::class.java)
            startActivity(intent)
        })

        searchActivity?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        })

        myLocation?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, MyLocationActivity::class.java)
            startActivity(intent)
        })

        SeeAllUpComing?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, UpComingEventActivity::class.java)
            startActivity(intent)
        })

        categoryMusic?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, MusicCategory::class.java)
            startActivity(intent)
        })
        

        
        return view
    }

    private fun getData() {
        getSliderList()
        getCategoryList()
        getTopList()
        thingsToDoList()
        upCommingEvent()
    }

    private fun upCommingEvent() {
        ApiClient.instence_user?.getUpComingEventList(UpComingEventRequest("Sikkim"))?.enqueue(object : Callback<UpcomingEventResponse> {
            override fun onFailure(call: Call<UpcomingEventResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<UpcomingEventResponse>, response: Response<UpcomingEventResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()!!.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.data?.let { upComingEventList.addAll(it) }
                        bindUpComingEventList(upComingEventList)
                    } else {
                        Toast.makeText(requireActivity(), ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "Something is wrong try again later", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun bindUpComingEventList(upComingEventList: ArrayList<UpcomingEventListData>) {
        upComingEventRecyclerView?.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = UpComingEventAdapter(requireActivity(), upComingEventList,this)
        upComingEventRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun thingsToDoList() {
        ApiClient.instence_user?.thingsToDoList(ThingsToDoRequest("Sikkim"))?.enqueue(object : Callback<ThingsToDoResponse> {
            override fun onFailure(call: Call<ThingsToDoResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<ThingsToDoResponse>, response: Response<ThingsToDoResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()!!.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.data?.let { thingsToDoList.addAll(it) }
                        bindThingsToDoList(thingsToDoList)
                    } else {
                        Toast.makeText(requireActivity(), ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "Something is wrong try again later", Toast.LENGTH_SHORT).show()

                }
            }
        })
    }

    private fun bindThingsToDoList(thingsToDoList: ArrayList<ThingsToDoListData>) {
        thingsToDoRecyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = ThinksToDoAdapterTwo(requireActivity(), thingsToDoList)
        thingsToDoRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getTopList() {
            ApiClient.instence_user?.getTopList(TopVisitedRequest("Sikkim"))?.enqueue(object : Callback<TopVisitedResponse> {
            override fun onFailure(call: Call<TopVisitedResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<TopVisitedResponse>, response: Response<TopVisitedResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()!!.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.data?.let { topVisitedList.addAll(it) }
                        bindTopVisitedList(topVisitedList)
                    } else {
                        Toast.makeText(requireActivity(), ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "Something is wrong try again later", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun bindTopVisitedList(topVisitedList: ArrayList<TopVisitedListData>) {
        topVisitedRecyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = TopVisitedAdapter(requireActivity(), topVisitedList,this)
        topVisitedRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getCategoryList() {
        ApiClient.instence_user?.getCategoryList?.enqueue(object : Callback<CategoryListResponse> {
            override fun onFailure(call: Call<CategoryListResponse>, t: Throwable) {
             Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<CategoryListResponse>, response: Response<CategoryListResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()!!.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.data?.let { categoryList.addAll(it) }
                        bindCategoryList(categoryList)
                    } else {
                        Toast.makeText(requireActivity(), ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "Something is wrong try again later", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun bindCategoryList(categoryList: ArrayList<CategoryListData>) {
        categoryRecyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = CategoryAdapter(requireActivity(), categoryList,this)
        categoryRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getSliderList() {
        ApiClient.instence_user?.appBannerList?.enqueue(object : Callback<BannerResponse> {
            override fun onFailure(call: Call<BannerResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<BannerResponse>, response: Response<BannerResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()!!.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.data?.let { bannerList.addAll(it) }
                        bindList(bannerList)
                    } else {
                        Toast.makeText(requireActivity(), ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "Something is wrong try again later", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun bindList(bannerList: ArrayList<BannerListData>) {
        val sliderAdapter = SliderAdapter(requireActivity(),bannerList,this)
        sliderView?.setSliderAdapter(sliderAdapter)
        sliderView?.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderView?.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView?.startAutoCycle()
    }

    private fun initView(view: View) {
        notifications = view.findViewById(R.id.notifications)
        moreAbout = view.findViewById(R.id.moreAbout)
        sliderView = view.findViewById(R.id.image_slider)
        searchActivity = view.findViewById(R.id.searchActivity)
        myLocation = view.findViewById(R.id.myLocation)
        placeLayout = view.findViewById(R.id.placeLayout)
        sellAllPlaces = view.findViewById(R.id.sellAllPlaces)
        SeeAllUpComing = view.findViewById(R.id.SeeAllUpComing)
        SeeAllToDo = view.findViewById(R.id.SeeAllToDo)
        upComingEventRecyclerView = view.findViewById(R.id.upComingEventRecyclerView)
        categoryMusic = view.findViewById(R.id.categoryMusic)
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView)
        topVisitedRecyclerView = view.findViewById(R.id.topVisitedRecyclerView)
        thingsToDoRecyclerView = view.findViewById(R.id.thingsToDoRecyclerView)
    }

    override fun userCategoryListner(id: String, name: String) {
        Toast.makeText(requireActivity(), ""+name, Toast.LENGTH_SHORT).show()
        val intent = Intent(requireActivity(), CategoryName::class.java)
        intent.putExtra("category_id",id)
        intent.putExtra("categoryName",name)
        startActivity(intent)
    }

    override fun userTopPlaceListner(id: String, name: String) {
        id.let {
            val intent = Intent(activity, PlaceDetails::class.java)
            intent.putExtra("id",id)
            intent.putExtra("city",name)

            startActivity(intent)
        }

    }

    override fun thingsToDoListner(id: String, name: String) {
        Toast.makeText(requireActivity(), ""+name, Toast.LENGTH_SHORT).show()
    }

    override fun upcomingEventListner(id: String, name: String) {
        Toast.makeText(requireActivity(), ""+name, Toast.LENGTH_SHORT).show()
    }

    override fun bannerListner(id: String) {
        val intent = Intent(activity, PlaceDetails::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
        Toast.makeText(requireActivity(), ""+id, Toast.LENGTH_SHORT).show()
    }
}