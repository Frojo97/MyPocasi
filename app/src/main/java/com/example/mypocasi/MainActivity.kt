package com.example.mypocasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // get reference to button
        val btnVyhledat = findViewById(R.id.btnVyhledat) as Button
        // set on-click listener
        btnVyhledat.setOnClickListener {
            // your code to perform when the user clicks on the button
            val intent = Intent(this, LookWeatherActivity::class.java)
            startActivity(intent)
        }


        val btnKonec = findViewById(R.id.btnKonec) as Button

        btnKonec.setOnClickListener{
            finish()
        }
    }
}