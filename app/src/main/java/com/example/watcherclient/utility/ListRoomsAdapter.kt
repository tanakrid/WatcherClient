package com.example.watcherclient.utility

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.watcherclient.R
import com.example.watcherclient.ShowDetailRoomActivity
import com.example.watcherclient.model.Room
import kotlinx.android.synthetic.main.card_room.view.*

class ListRoomsAdapter(private val dataSet: Map<String, Room>, private val listener: (Map<String, Room>) -> Unit): RecyclerView.Adapter<ListRoomsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val roomCardView: CardView = view.findViewById(R.id.roomCardView)
        val roomNameTextView: TextView = view.findViewById(R.id.roomNameTextView)
        val statusValueTextView: TextView = view.findViewById(R.id.onlineStatusTextView)
        val currentPersonNumberValueTextView: TextView = view.findViewById(R.id.currentPersonNumberValueTextView)
        val descValueTextView: TextView = view.findViewById(R.id.descValueTextView)
        val numCameraValueTextView: TextView = view.findViewById(R.id.numCameraValueTextView)
        val numMemberValueTextView: TextView = view.findViewById(R.id.numMemberValueTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_room, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val values: List<Room> = dataSet.values.toList()
        val keys: List<String> = dataSet.keys.toList()
        holder.roomNameTextView.text = values[position].room_name
        holder.currentPersonNumberValueTextView.text = values[position].curr_num.toString()
        holder.descValueTextView.text = values[position].desc
        holder.numCameraValueTextView.text = values[position].devices_id.size.toString()
        holder.numMemberValueTextView.text = values[position].members_id.size.toString()

        holder.roomCardView.setOnClickListener {
            val item = HashMap<String, Room>()
            val key = keys[position]
            item[key] = values[position]
            listener(item)
        }
    }
}