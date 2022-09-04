package com.eminence.eventit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.eminence.eventit.ui.dashboard.DashboardFragment
import com.eminence.eventit.ui.home.HomeFragment
import com.eminence.eventit.ui.notifications.NotificationsFragment

class MainActivity : AppCompatActivity() {

  //  private var binding: ActivityMainBinding? = null
  lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
       var afterlogin = intent.getIntExtra("afterLogin",0)
        if (afterlogin==1){
            loadFragment(NotificationsFragment())
        }else{
            loadFragment(HomeFragment())
        }
        bottomNav = findViewById(R.id.nav_view) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    loadFragment(DashboardFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    loadFragment(NotificationsFragment())
                    return@setOnItemSelectedListener true
                }
                else -> { false}
            }


            /*  binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()
        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
            .build()
        val navController = findNavController(this, R.id.nav_host_fragment_activity_main)
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        binding?.navView?.let { setupWithNavController(it, navController) }

       */


        }
    }



    private fun loadFragment(fragment: Fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace( R.id.nav_host_fragment_activity_main, fragment)
        ///    transaction.addToBackStack(null)
            transaction.commit()


        }

}