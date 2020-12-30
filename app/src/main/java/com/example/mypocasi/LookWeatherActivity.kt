package com.example.mypocasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.net.URL
import org.json.JSONObject
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import android.os.AsyncTask
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class LookWeatherActivity : AppCompatActivity() {
    var hledaneMesto: String = ""
    val appid: String = "294cb0ec8fe0f03e56f7b5250d5d7030"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_weather)
        supportActionBar?.hide()
        //pocasi().execute()


        val btnHledat = findViewById(R.id.btnHledat) as Button
        btnHledat.setOnClickListener {
            findViewById<TextView>(R.id.tvPrazdny).visibility = View.GONE
            var etHledat = findViewById<EditText>(R.id.etHledat).text
            hledaneMesto = etHledat.toString()
            if (hledaneMesto.length > 0)
                pocasi().execute()
            else
                findViewById<TextView>(R.id.tvPrazdny).visibility = View.VISIBLE
        }
    }

    inner class pocasi() : AsyncTask<String, Void, String>(){
    /*override fun onPreExecute() {
        super.onPreExecute()
        //findViewById<ProgressBar>(R.id.nacitani).visibility = View.VISIBLE
        //findViewById<ConstraintLayout>(R.id.hlavniController).visibility = View.GONE
        findViewById<TextView>(R.id.tvChyba).visibility = View.GONE

    }
*/
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
                val teplotaMin = main.getString("temp_min") + "°C"
                val teplotaMax = main.getString("temp_max") + "°C"
                val vychodSlunce : Long = sys.getLong("sunrise")
                val zapadSlunce : Long = sys.getLong("sunset")
                val vitrRychlost = vitr.getString("speed")
                val pocasiPopis = pocasi.getString("description")
                val mesto = jsonObj.getString("name")
                val zeme = sys.getString("country")
                val formatter: DateFormat = SimpleDateFormat("HH:mm:ss")
                formatter.setTimeZone(TimeZone.getTimeZone("CET"))


                findViewById<TextView>(R.id.tvMestoText).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvMesto).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvZemeText).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvZeme).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvTemp).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvMinText).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvMin).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvMaxText).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvMax).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvRychlostText).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvSpeedWind).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvVychodText).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvStartSun).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvZapadText).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvEndSun).visibility = View.VISIBLE


                findViewById<TextView>(R.id.tvMin).text = teplotaMin
                findViewById<TextView>(R.id.tvMax).text = teplotaMax
                findViewById<TextView>(R.id.tvTemp).text = teplota
                findViewById<TextView>(R.id.tvMesto).text = mesto
                findViewById<TextView>(R.id.tvZeme).text = zeme
                // findViewById<TextView>(R.id.tvZeme).text = pocasiPopis
                findViewById<TextView>(R.id.tvSpeedWind).text = vitrRychlost + " m/s"
                findViewById<TextView>(R.id.tvStartSun).text = formatter.format(vychodSlunce*1000)
                findViewById<TextView>(R.id.tvEndSun).text = formatter.format(zapadSlunce*1000)

                findViewById<ProgressBar>(R.id.pbNacitani).visibility = View.GONE
                findViewById<ConstraintLayout>(R.id.clHlavni).visibility = View.VISIBLE
            }
            catch (e: Exception) {
                findViewById<ProgressBar>(R.id.pbNacitani).visibility = View.GONE
                findViewById<ConstraintLayout>(R.id.clHlavni).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvChyba).visibility = View.VISIBLE
            }
        }
    }
}