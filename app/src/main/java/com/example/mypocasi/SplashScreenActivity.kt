package com.example.mypocasi

import android.content.Intent
import android.graphics.drawable.Animatable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView

class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        val startLogo = AnimationUtils.loadAnimation(this, R.anim.start_logo)
        val tvStartLogo = findViewById(R.id.tvStartLogo) as TextView
        tvStartLogo.startAnimation(startLogo)
        handler = Handler()
        handler.postDelayed({
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 3000)
    }
}