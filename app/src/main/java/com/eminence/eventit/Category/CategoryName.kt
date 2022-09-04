package com.eminence.eventit.Category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eminence.eventit.R
import android.content.Intent
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eminence.eventit.Adapters.CategoryListByCityAdapter
import com.eminence.eventit.Category.model.categoryListByCity.CategoryListByCityData
import com.eminence.eventit.Category.model.categoryListByCity.CategoryListByCityRequest
import com.eminence.eventit.Category.model.categoryListByCity.CategoryListByCityResponse
import com.eminence.eventit.SearchActivity
import com.eminence.eventit.interfaces.CategoryListByCityListner
import com.eminence.eventit.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class CategoryName : AppCompatActivity() , CategoryListByCityListner {
    var back_img: ImageView? = null
    var searchActivity: ImageView? = null
    var txtCategoryName: TextView? = null

    private var categoryRecyclerView: RecyclerView? = null
    private var categoryListByCity: ArrayList<CategoryListByCityData> = ArrayList()
    private var categoryListById: ArrayList<CategoryListByIdData> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_name)
        supportActionBar!!.hide()
        initView()
        val CategoryName = intent.getStringExtra("categoryName")
        val category_id = intent.getStringExtra("category_id")
        txtCategoryName?.text = CategoryName
        getCategorListById(category_id)
     //   getCategoryList()

        back_img?.setOnClickListener{ onBackPressed() }
        searchActivity?.setOnClickListener{
            val intent = Intent(applicationContext, SearchActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getCategorListById(category_id: String?) {
        ApiClient.instence_user?.getCategoryListById(CategoryListByIdRequest(category_id.toString()))?.enqueue(object :
        Callback<CategoryListByIdResponse>{
            override fun onResponse(
                call: Call<CategoryListByIdResponse>,
                response: Response<CategoryListByIdResponse>
            ) {
                if (response.isSuccessful){
                    val status = response.body()?.status.toString()
                    if (response.isSuccessful) {
                        val status = response.body()?.status.toString()
                        if (status.equals("1",true)) {
                            response.body()?.data?.let { categoryListById.addAll(it) }
                            bindCategoryIdList(categoryListById)
                        } else {
                            Toast.makeText(this@CategoryName, ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@CategoryName, "Something is wrong try again later", Toast.LENGTH_SHORT).show()

                    }
            }
            }

            override fun onFailure(call: Call<CategoryListByIdResponse>, t: Throwable) {
                Log.d("CategoryListById",""+t.localizedMessage)

            }
        })
    }

    private fun bindCategoryIdList(categoryListById: ArrayList<CategoryListByIdData>) {
        categoryRecyclerView?.layoutManager = GridLayoutManager(this@CategoryName, 3)
        val adapter = CategoryListByIdAdapter(this@CategoryName, categoryListById,this)
        categoryRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getCategoryList() {
        ApiClient.instence_user?.getCategoryListByCity(CategoryListByCityRequest("sikkim"))?.enqueue(object :
            Callback<CategoryListByCityResponse> {
            override fun onFailure(call: Call<CategoryListByCityResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<CategoryListByCityResponse>, response: Response<CategoryListByCityResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()?.status.toString()
                    if (status.equals("1",true)) {
                        response.body()?.data?.let { categoryListByCity.addAll(it) }
                        bindCategoryList(categoryListByCity)
                    } else {
                        Toast.makeText(this@CategoryName, ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@CategoryName, "Something is wrong try again later", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun bindCategoryList(categoryListByCity: ArrayList<CategoryListByCityData>) {
        categoryRecyclerView?.layoutManager = GridLayoutManager(this@CategoryName, 3)
        val adapter = CategoryListByCityAdapter(this@CategoryName, categoryListByCity,this)
        categoryRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun initView() {
        back_img = findViewById(R.id.back_img)
        searchActivity = findViewById(R.id.searchActivity)
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView)
        txtCategoryName = findViewById(R.id.txtCategoryName)

    }

    override fun categoryListner(id: String, name: String) {
        Toast.makeText(this@CategoryName, ""+name, Toast.LENGTH_SHORT).show()
    }
}