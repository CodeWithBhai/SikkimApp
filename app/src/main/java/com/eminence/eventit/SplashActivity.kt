package com.eminence.eventit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {
    var handler: Handler? = null
    var splashScreenImage: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()
        splashScreenImage = findViewById(R.id.splashScreenImage)
        val animation = AnimationUtils.loadAnimation(this, R.anim.top_waves)
        splashScreenImage?.setAnimation(animation)
        handler = Handler()
        handler?.postDelayed({
            val intent = Intent(this@SplashActivity, WelcomeActivity::class.java)
            val hyperspaceJump = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.zoom_out)
            splashScreenImage?.startAnimation(hyperspaceJump)
            startActivity(intent)
            finish()
        }, 3000)
    }
}