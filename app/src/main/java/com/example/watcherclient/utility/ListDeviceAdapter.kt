package com.example.watcherclient.utility

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.watcherclient.R
import com.example.watcherclient.model.Device

class ListDeviceAdapter(private var dataSet: List<Device>): RecyclerView.Adapter<ListDeviceAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        val deviceIDTextView: TextView = view.findViewById(R.id.memberNameTextView)
        val deviceStatusTextView: TextView = view.findViewById(R.id.deviceStatusTextView)
        val deviceStatusCardView: CardView = view.findViewById(R.id.deviceStatusCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_device, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.deviceIDTextView.text = dataSet[position].device_id
        val status = dataSet[position].is_active

        var textStatus = ""
        if(status){
            textStatus = "on"
        }else{
            textStatus = "off"
        }

        holder.deviceStatusTextView.text = textStatus
        if (status){
            holder.deviceStatusCardView.setCardBackgroundColor(Color.GREEN)
        }else{
            holder.deviceStatusCardView.setCardBackgroundColor(Color.RED)
        }

    }
}