package com.eminence.eventit.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eminence.eventit.Adapters.ThinksToDoAdapterTwo
import com.eminence.eventit.R
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoListData
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoRequest
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoResponse
import com.eminence.eventit.utils.ApiClient
import retrofit2.Call
import retrofit2.Response

class SeeAllThinkToDo : AppCompatActivity() {
    private var txtPlaceName: TextView? = null

    private var categoryRecyclerView: RecyclerView? = null
    private var thinkToDo: ArrayList<ThingsToDoListData> = ArrayList()
    var recycler_view_to_do: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txtPlaceName = findViewById(R.id.txtTitle1)
        setContentView(R.layout.activity_see_all_think_to_do)
        categoryRecyclerView = findViewById(R.id.recycler_view_to_do)
        supportActionBar!!.hide()
        getCategoryList()

    }

    private fun getCategoryList() {
        ApiClient.instence_user?.thingsToDoList(ThingsToDoRequest("sikkim"))?.enqueue(object :
                retrofit2.Callback<ThingsToDoResponse> {
            override fun onFailure(call: Call<ThingsToDoResponse>, t: Throwable) {
                Log.d("error",""+t.localizedMessage)
            }

            override fun onResponse(call: Call<ThingsToDoResponse>, response: Response<ThingsToDoResponse>) {
                if (response.isSuccessful) {
                    val status = response.body()!!. status.toString()
                    if (status.equals("1")){
                        thinkToDo.addAll(response.body()!!.data)
                        bindCategoryList(thinkToDo)
                    }else{
                    }

                } else {
                    Toast.makeText(this@SeeAllThinkToDo, "Something is wrong try again later", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun bindCategoryList(thinkToDo: ArrayList<ThingsToDoListData>) {
        categoryRecyclerView!!.layoutManager = GridLayoutManager(this@SeeAllThinkToDo, 3)
        val adapter = ThinksToDoAdapterTwo(this@SeeAllThinkToDo, thinkToDo)
        categoryRecyclerView!!.adapter = adapter
        adapter.notifyDataSetChanged()
        Toast.makeText(this@SeeAllThinkToDo, "data is showing", Toast.LENGTH_SHORT).show()
    }

    fun back(view: View?) {
        onBackPressed()
    }
}