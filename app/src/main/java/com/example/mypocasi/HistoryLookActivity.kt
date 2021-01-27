package com.example.mypocasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryLookActivity : AppCompatActivity() {
    
    private lateinit var viewModelHistory: HistoryLookViewModel

    var historyMesta : List<String> = listOf()
    var historyZeme : List<String> = listOf()
    var historyTeplota : List<String> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_look)
        supportActionBar?.hide()

        viewModelHistory = ViewModelProvider(this).get(HistoryLookViewModel::class.java)
        viewModelHistory.firstMesto.observe(this, {
            historyMesta = it.nazevMesta.trim().split(';')
            historyZeme = it.zeme.trim().split(';')
            historyTeplota = it.teplota.trim().split(';')

            val exampleList = generateDummyList(historyMesta.size)
            findViewById<RecyclerView>(R.id.recycler_view).adapter = ItemAdapter(exampleList)
            findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(this)
        })
    }

    fun generateDummyList(size: Int): List<ItemActivity> {
        val list = ArrayList<ItemActivity>()

        for (i in 0 until size){
            val item = ItemActivity(historyMesta[i] + ", " + historyZeme[i], historyTeplota[i])
            list += item
        }
        return list
    }
}