package com.eminence.eventit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eminence.eventit.R
import android.content.Intent
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.eminence.eventit.MainActivity

class SecondSplashActivity : AppCompatActivity() {

    var splashScreenImage: ImageView? = null
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_splash)
        supportActionBar?.hide()
        splashScreenImage = findViewById(R.id.splashScreenImage1)
        val animation = AnimationUtils.loadAnimation(this, R.anim.top_waves)
        splashScreenImage?.setAnimation(animation)
        handler = Handler()
        handler!!.postDelayed({
            val intent = Intent(this@SecondSplashActivity, MainActivity::class.java)
            val hyperspaceJump =
                AnimationUtils.loadAnimation(this@SecondSplashActivity, R.anim.zoom_out)
            splashScreenImage?.startAnimation(hyperspaceJump)
            startActivity(intent)
            finish()
        }, 3000)
    }
}