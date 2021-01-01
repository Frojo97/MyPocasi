package com.example.mypocasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HistoryLookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_look)
        supportActionBar?.hide()
    }

    fun loadHistory(){

    }
}