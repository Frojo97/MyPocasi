package com.example.mypocasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val btnVyhledat = findViewById(R.id.btnVyhledat) as Button
        btnVyhledat.setOnClickListener {
            val intent = Intent(this, LookWeatherActivity::class.java)
            startActivity(intent)
        }

        val btnKonec = findViewById(R.id.btnKonec) as Button
        btnKonec.setOnClickListener{
            finish()
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(applicationContext, "Opětovným stisknutím tlačítka zpět aplikaci ukončíte", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}