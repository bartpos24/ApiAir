package com.example.airapi.ui.sensor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.airapi.DAO.ApiService
import com.example.airapi.R
import com.example.airapi.models.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SensorActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewMenager:RecyclerView.LayoutManager
    private lateinit var viewAdapter: SensorAdapter

    var sensors: List<Model.Sensors> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        val retrofit = Retrofit.Builder().baseUrl("http://api.gios.gov.pl/pjp-api/rest/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(ApiService::class.java)

        viewMenager = LinearLayoutManager(this)
        viewAdapter = SensorAdapter(sensors) {
            Toast.makeText(this, "${it.id} Clicked", Toast.LENGTH_SHORT).show()
        }
        recyclerView = findViewById<RecyclerView>(R.id.recyclerSensor) as RecyclerView
        recyclerView.layoutManager = viewMenager
        recyclerView.adapter = viewAdapter

        val sensorId = intent.getIntExtra("MAIN_ID", -1)

        api.fetchAllSensors(sensorId).enqueue(object : Callback<List<Model.Sensors>> {
            override fun onFailure(call: Call<List<Model.Sensors>>, t: Throwable) {
                Log.e("blad", "onFailure: ${t.message}")
            }

            override fun onResponse(call: Call<List<Model.Sensors>>, response: Response<List<Model.Sensors>>) {
                Log.i("dupa", "onResponse: ${response.body()!![0].id}")
                sensors = response.body()!!
                viewAdapter = SensorAdapter(sensors){
                }
                recyclerView.adapter = viewAdapter
            }
        })
    }
}
