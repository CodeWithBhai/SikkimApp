package com.eminence.eventit.HomeActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eminence.eventit.Activities.details.models.Banner
import com.eminence.eventit.Activities.details.models.Gallery
import com.eminence.eventit.Activities.details.models.PlaceDetailsResponse
import com.eminence.eventit.Partner.AddedBookingActivity
import com.eminence.eventit.R
import com.eminence.eventit.utils.Constants.IMAGEURL

class EventAboutActivity : AppCompatActivity() {

    private var imageMainImage: ImageView? = null
    private var txtPlaceNameType: TextView? = null
    private var txtAbout: TextView? = null
    private var txtName: TextView? = null
    var proceedToPayment: ImageView? = null
    var txtabout: TextView? = null
    var txtAddOnServicePrice: TextView? = null
    var frontMain: CardView? = null
    var price_amount: LinearLayout? = null
    var goToBookingg: LinearLayout? = null
    private var place: TextView? = null
    private var bannerList: ArrayList<Banner> = ArrayList()
    private var placedDetails: ArrayList<PlaceDetailsResponse> = ArrayList()
    private var galleryList: ArrayList<Gallery> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_about)

        supportActionBar?.hide()
        //actionBar?.setDisplayHomeAsUpEnabled(true)
        //actionBar?.setDisplayShowHomeEnabled(true)
        initView()

        val catId = intent.getStringExtra("id").toString()
        val txtname = intent.getStringExtra("name")
        val location = intent.getStringExtra("location")
        val aboutTxt = intent.getStringExtra("about")
        val place_id = intent.getStringExtra("place")
        val main_image = intent.getStringExtra("main_image")
        val price_show = intent.getStringExtra("price")
        val Date_show = intent.getStringExtra("dates")

        txtPlaceNameType?.text = txtname
        txtAbout?.text = aboutTxt
        txtName?.text = location
        txtAddOnServicePrice?.text = price_show
        //image show

        imageMainImage?.let {
            Glide.with(this@EventAboutActivity)
                    .load(IMAGEURL.toString() + main_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(it)
        }

        // imageMainImage?.setImageResource(imageShow)

        goToBookingg?.setOnClickListener{
            val intent = Intent(applicationContext, AddedBookingActivity::class.java)
            startActivity(intent)
        }

    }


    private fun initView() {

        imageMainImage = findViewById(R.id.imageMainImage)
        txtPlaceNameType = findViewById(R.id.txtPlaceName)
        txtAbout = findViewById(R.id.txtAbout)
        txtName = findViewById(R.id.place)
        txtAddOnServicePrice = findViewById(R.id.txtAddOnServicePrice)
        proceedToPayment = findViewById(R.id.proceedToPayment)
        goToBookingg = findViewById(R.id.goToBookingg)
        frontMain = findViewById(R.id.frontMain)
        price_amount = findViewById(R.id.price_amount)


    }
    fun back(view: View?) {
        onBackPressed()
    }
}