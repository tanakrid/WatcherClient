package com.example.watcherclient.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.watcherclient.R
import com.example.watcherclient.model.DevicesInRoom
import com.example.watcherclient.model.MemberInRoom

class ListDeviceRoomsAdapter(private val dataSet: Map<String, DevicesInRoom>, private val listener: (position: Int) -> Unit): RecyclerView.Adapter<ListDeviceRoomsAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val roomNameTextView : TextView = view.findViewById(R.id.roomNameTextView)
        val numDeviceTextView : TextView = view.findViewById(R.id.numDeviceTextView)
        val deviceInRoomCardView : CardView = view.findViewById(R.id.deviceInRoomCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDeviceRoomsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_device_room, parent, false)
        return ListDeviceRoomsAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val values: List<DevicesInRoom> = dataSet.values.toList()
        val keys: List<String> = dataSet.keys.toList()

        holder.roomNameTextView.text = values[position].room_name
        holder.numDeviceTextView.text = values[position].devices.size.toString()

        holder.deviceInRoomCardView.setOnClickListener {
            listener(position)
        }

    }
}