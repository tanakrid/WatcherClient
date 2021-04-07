package com.example.watcherclient.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.watcherclient.R
import com.example.watcherclient.model.Device

class ListDeviceInRoomAdapter(private val dataSet: List<Device>, private val listener: (position: Int) -> Unit): RecyclerView.Adapter<ListDeviceInRoomAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val removeButton : Button = view.findViewById(R.id.removeButton)
        val deviceNameTextView : TextView = view.findViewById(R.id.deviceNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_device_in_room, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.deviceNameTextView.text = dataSet[position].device_id

        holder.removeButton.setOnClickListener {
            listener(position)
        }
    }
}