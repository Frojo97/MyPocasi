package com.example.mypocasi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.net.URL
import org.json.JSONObject
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import java.sql.Struct

class LookWeatherActivity : AppCompatActivity() {
    var hledaneMesto: String = ""
    val appid: String = "294cb0ec8fe0f03e56f7b5250d5d7030"

    private var mestoArray: String = ""
    private var zemeArray: String = ""
    private var teplotaArray: String = ""

    private lateinit var historyViewModel: HistoryLookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_weather)
        supportActionBar?.hide()
        nactiData()

        val btnHledat = findViewById(R.id.btnHledat) as Button
        btnHledat.setOnClickListener {
            hideKeyboard()
            findViewById<TextView>(R.id.tvPrazdny).visibility = View.GONE
            var etHledat = findViewById<EditText>(R.id.etHledat).text
            hledaneMesto = etHledat.toString()
            if (hledaneMesto.length > 0)
                pocasi().execute()
            else {
                findViewById<TextView>(R.id.tvChyba).visibility = View.GONE
                findViewById<TextView>(R.id.tvPrazdny).visibility = View.VISIBLE
                goneItem()
            }
        }
    }

    private fun hideKeyboard(){
        val view = this.currentFocus
        if (view != null){
            val hideKey = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideKey.hideSoftInputFromWindow(view.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    fun visibleItem(){
        findViewById<TextView>(R.id.tvMestoText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvMesto).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvZemeText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvZeme).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvTemp).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvTextPocasi).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvMinText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvMin).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvMaxText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvMax).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvPocitoveText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvPocitove).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvRychlostText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvSpeedWind).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvVychodText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvStartSun).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvZapadText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvEndSun).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvTlakText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvTlak).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvVlhkostText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvVlhkost).visibility = View.VISIBLE
    }

    fun goneItem(){
        findViewById<TextView>(R.id.tvMestoText).visibility = View.GONE
        findViewById<TextView>(R.id.tvMesto).visibility = View.GONE
        findViewById<TextView>(R.id.tvZemeText).visibility = View.GONE
        findViewById<TextView>(R.id.tvZeme).visibility = View.GONE
        findViewById<TextView>(R.id.tvTemp).visibility = View.GONE
        findViewById<TextView>(R.id.tvTextPocasi).visibility = View.GONE
        findViewById<TextView>(R.id.tvMinText).visibility = View.GONE
        findViewById<TextView>(R.id.tvMin).visibility = View.GONE
        findViewById<TextView>(R.id.tvMaxText).visibility = View.GONE
        findViewById<TextView>(R.id.tvMax).visibility = View.GONE
        findViewById<TextView>(R.id.tvPocitoveText).visibility = View.GONE
        findViewById<TextView>(R.id.tvPocitove).visibility = View.GONE
        findViewById<TextView>(R.id.tvRychlostText).visibility = View.GONE
        findViewById<TextView>(R.id.tvSpeedWind).visibility = View.GONE
        findViewById<TextView>(R.id.tvVychodText).visibility = View.GONE
        findViewById<TextView>(R.id.tvStartSun).visibility = View.GONE
        findViewById<TextView>(R.id.tvZapadText).visibility = View.GONE
        findViewById<TextView>(R.id.tvEndSun).visibility = View.GONE
        findViewById<TextView>(R.id.tvTlakText).visibility = View.GONE
        findViewById<TextView>(R.id.tvTlak).visibility = View.GONE
        findViewById<TextView>(R.id.tvVlhkostText).visibility = View.GONE
        findViewById<TextView>(R.id.tvVlhkost).visibility = View.GONE
    }

    fun nactiData(){
        historyViewModel = ViewModelProvider(this).get(HistoryLookViewModel::class.java)
        historyViewModel.firstMesto.observe(this) {
            mestoArray = it.nazevMesta
            zemeArray = it.zeme
            teplotaArray = it.teplota
            //Log.d("Mesta:", it.nazevMesta)
        }
    }

    fun saveDate(mesto: String, zeme: String, teplota: String){
        add(mesto, zeme, teplota)
        historyViewModel.updateValue(mestoArray, zemeArray, teplotaArray)
    }

    fun add(mesto : String, zeme : String, teplota : String){
        if (mestoArray.length != 0) {
            mestoArray = mestoArray + ";" + mesto
            zemeArray = zemeArray + ";" + zeme
            teplotaArray = teplotaArray + ";" + teplota
        }
        else {
            mestoArray += mesto
            zemeArray += zeme
            teplotaArray += teplota
        }
    }

    inner class pocasi() : AsyncTask<String, Void, String>(){
    override fun onPreExecute() {
        super.onPreExecute()
        findViewById<ProgressBar>(R.id.pbNacitani).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvChyba).visibility = View.GONE
        goneItem()
    }
        override fun doInBackground(vararg p0: String?) : String? {
            var odkaz :String?

            try {
                odkaz = URL("https://api.openweathermap.org/data/2.5/weather?q=$hledaneMesto&units=metric&appid=$appid").readText(Charsets.UTF_8)
            }
            catch (e: Exception){
                odkaz = null
            }
            return odkaz
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                var jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val vitr = jsonObj.getJSONObject("wind")
                val pocasi = jsonObj.getJSONArray("weather").getJSONObject(0)
                val teplota = main.getString("temp") + "°C"
                val teplotaPocit = main.getString("feels_like") + "°C"
                val teplotaMin = main.getString("temp_min") + "°C"
                val teplotaMax = main.getString("temp_max") + "°C"
                val tlak = main.getString("pressure") + " hPa"
                val vlhkostVzduchu = main.getString("humidity") + "%"
                val vychodSlunce : Long = sys.getLong("sunrise")
                val zapadSlunce : Long = sys.getLong("sunset")
                val vitrRychlost = vitr.getString("speed")
                val pocasiPopis = pocasi.getString("description")
                val mesto = jsonObj.getString("name")
                val zeme = sys.getString("country")
                val formatter: DateFormat = SimpleDateFormat("HH:mm:ss")
                formatter.setTimeZone(TimeZone.getTimeZone("CET"))

                visibleItem()

                findViewById<TextView>(R.id.tvMin).text = teplotaMin
                findViewById<TextView>(R.id.tvMax).text = teplotaMax
                findViewById<TextView>(R.id.tvTemp).text = teplota
                findViewById<TextView>(R.id.tvMesto).text = mesto
                findViewById<TextView>(R.id.tvZeme).text = zeme
                findViewById<TextView>(R.id.tvTextPocasi).text = pocasiPopis
                findViewById<TextView>(R.id.tvSpeedWind).text = vitrRychlost + " m/s"
                findViewById<TextView>(R.id.tvStartSun).text = formatter.format(vychodSlunce*1000)
                findViewById<TextView>(R.id.tvEndSun).text = formatter.format(zapadSlunce*1000)
                findViewById<TextView>(R.id.tvPocitove).text = teplotaPocit
                findViewById<TextView>(R.id.tvTlak).text = tlak
                findViewById<TextView>(R.id.tvVlhkost).text = vlhkostVzduchu

                findViewById<ProgressBar>(R.id.pbNacitani).visibility = View.GONE
                findViewById<ConstraintLayout>(R.id.clHlavni).visibility = View.VISIBLE
                saveDate(mesto, zeme, teplota)
            }
            catch (e: Exception) {
                findViewById<ProgressBar>(R.id.pbNacitani).visibility = View.GONE
                findViewById<ConstraintLayout>(R.id.clHlavni).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvChyba).visibility = View.VISIBLE
            }
        }
    }
}