package com.example.mypocasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe

class HistoryLookActivity : AppCompatActivity() {

    private val adapter = ItemAdapter()
    private lateinit var viewModelHistory: HistoryLookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_look)
        supportActionBar?.hide()

        /*viewModelHistory = ViewModelProvider(this).get(HistoryLookViewModel::class.java)
        viewModelHistory.firstMesto.observe(this,{
           // findViewById<TextView>(R.id.tv_mesto).text = it.nazevMesta
           /* Log.d("Mesto", it.nazevMesta)
            Log.d("Zeme", it.zeme)
            Log.d("Teplota", it.teplota)*/
        })*/

        viewModelHistory = ViewModelProvider(this).get(HistoryLookViewModel::class.java)
        viewModelHistory.firstMesto.observe(owner = this){
            taskUiModel -> adapter.submitList(taskUiModel.tasks)
        }
    }

    fun loadHistory(){

    }
}