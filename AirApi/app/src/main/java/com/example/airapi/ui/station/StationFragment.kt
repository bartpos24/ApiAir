package com.example.airapi.ui.station

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.airapi.DAO.ApiService
import com.example.airapi.DAO.ModelDao

import com.example.airapi.DAO.Repository
import com.example.airapi.R
import com.example.airapi.models.Model
import kotlinx.android.synthetic.main.fragment_station.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StationFragment : Fragment() {

    private lateinit var stationViewModel: StationViewModel
    private lateinit var stationViewModelFactory: StationViewModelFactory
    private lateinit var stationAdapter: StationAdapter
    private lateinit var stationMenager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var dao: ModelDao

    var airs: List<Model.Stations> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val retrofit = Retrofit.Builder().baseUrl("http://api.gios.gov.pl/pjp-api/rest/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(ApiService::class.java)
        val repository = Repository.getInstance(dao)
        stationViewModelFactory = StationViewModelFactory(repository)
        val stationViewModel = ViewModelProviders.of(this, stationViewModelFactory).get(StationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_station, container, false)
        stationMenager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        stationAdapter = StationAdapter(airs) {
            Toast.makeText(context, "${it.name} Clicked", Toast.LENGTH_SHORT).show()
        }
        recyclerView = root.findViewById(R.id.recyclerStation) as RecyclerView
        recyclerView.layoutManager = stationMenager
        recyclerView.adapter = stationAdapter

        api.fetchAllStation().enqueue(object : Callback<List<Model.Stations>>{
            override fun onFailure(call: Call<List<Model.Stations>>, t: Throwable) {
                Log.e("blad", "onFailure: ${t.message}")
            }

            override fun onResponse(call: Call<List<Model.Stations>>, response: Response<List<Model.Stations>>) {
                Log.i("blad", "onResponse: ${response.body()!![0].name}")
                airs = response.body()!!
                stationAdapter = StationAdapter(airs){

                }
                recyclerView.adapter = stationAdapter
            }
        })

        val textView: TextView = root.findViewById(R.id.text_station)

        stationViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }
}