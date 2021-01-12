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
import androidx.recyclerview.widget.RecyclerView

class HistoryLookActivity : AppCompatActivity() {
    
    private lateinit var viewModelHistory: HistoryLookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_look)
        supportActionBar?.hide()

       // private val adapter = ItemAdapter(exampleList)
        
        /*viewModelHistory = ViewModelProvider(this).get(HistoryLookViewModel::class.java)
        viewModelHistory.firstMesto.observe(this,{
           // findViewById<TextView>(R.id.tv_mesto).text = it.nazevMesta
           /* Log.d("Mesto", it.nazevMesta)
            Log.d("Zeme", it.zeme)
            Log.d("Teplota", it.teplota)*/
        })*/
        val list: List<String> = emptyList()
        var hodnota: Int = 0
        //var list = null
        //val adapter = null

        var listView = findViewById(R.id.recycler_view) as ListView


        viewModelHistory = ViewModelProvider(this).get(HistoryLookViewModel::class.java)
        viewModelHistory.firstMesto.observe(this, {
            //val listHodnot = ListAdapter(this, android.R.layout.simple_list_item_1, it.nazevMesta + ", " + it.zeme + " = " + it.teplota)
            //list.toMutableList().add(android.R.layout.simple_list_item_1, (it.nazevMesta + ", " + it.zeme + " = " + it.teplota).trim().split(';').toString())
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,  it.nazevMesta.trim().split(';'))
            listView.adapter = adapter
            //hodnota = adapter.count
            //println("hodnota " + hodnota)
            // println("adapter " + list)
            println(it.nazevMesta + ", " + it.zeme + " = " + it.teplota)
            for(i in 0 until listOf(it).size) {
                println("pokus hokus " + " i = " + i + " " + listOf(it)[i].nazevMesta)
            }
            /*Log.d("Mesto", it.nazevMesta)
            Log.d("Zeme", it.zeme)
            Log.d("Teplota", it.teplota)*/
        })
        println("hodno")

       // val exampleList = generateDummyList(hodnota)
       // val exampleList = generateDummyList(list.size)
      //  findViewById<RecyclerView>(R.id.recycler_view).adapter = ItemAdapter(exampleList)
       // viewModelHistory.firstMesto.observe(owner = this){
            //taskUiModel -> adapter.submitList(taskUiModel.tasks)
    }

    fun generateDummyList(size: Int): List<ItemActivity> {
        val list = ArrayList<ItemActivity>()

        for (i in 0 until size){
            val item = ItemActivity(list[i].toString(), "pokus")
            list += item
        }
        return list
    }
}