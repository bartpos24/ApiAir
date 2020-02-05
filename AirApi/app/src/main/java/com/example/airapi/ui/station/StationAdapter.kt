package com.example.airapi.ui.station

import android.content.Intent
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airapi.R
import com.example.airapi.models.Model
import com.example.airapi.models.User
import com.example.airapi.ui.sensor.SensorActivity
import kotlinx.android.synthetic.main.item_station.view.*

class StationAdapter(val airs: List<Model.Stations>, val listener: (Model.Stations) -> Unit) : RecyclerView.Adapter<StationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_station, parent, false)
        return StationViewHolder(cellForRow)
    }

    override fun getItemCount(): Int = airs.size
    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(airs.get(position), listener)
        holder.itemView.setOnClickListener {
            Log.i("sasa", "StationAdapter: ${airs.get(position)}")
            val intent = Intent(holder.itemView.context, SensorActivity::class.java)
            intent.putExtra("MAIN_ID", airs.get(position).id)
            holder.itemView.context.startActivity(intent)
        }
    }
}

class StationViewHolder( itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(station: Model.Stations, listener: (Model.Stations) -> Unit) = with(itemView){
        tv_title.text = station.name
        tv_count.text = station.id.toString()
        tv_points.text = "99"
    }

}