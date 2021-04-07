package com.example.watcherclient.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.watcherclient.R
import com.example.watcherclient.model.MemberInRoom

class ListMemberRoomsAdapter(private val dataSet: Map<String, MemberInRoom>, private val listener: (position: Int) -> Unit): RecyclerView.Adapter<ListMemberRoomsAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val roomNameTextView : TextView = view.findViewById(R.id.roomNameTextView)
        val numPersonTextView : TextView = view.findViewById(R.id.numPersonTextView)
        val roomCardView: CardView = view.findViewById(R.id.roomCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMemberRoomsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_member_room, parent, false)
        return ListMemberRoomsAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val values: List<MemberInRoom> = dataSet.values.toList()
        val keys: List<String> = dataSet.keys.toList()

        holder.roomNameTextView.text = values[position].room_name
        holder.numPersonTextView.text = values[position].members.size.toString()
        holder.roomCardView.setOnClickListener {
            listener(position)
        }
    }
}