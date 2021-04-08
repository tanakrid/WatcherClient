package com.example.watcherclient.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.watcherclient.R
import com.example.watcherclient.model.Person

class ListMemberInRoomAdapter(private val dataSet: List<Person>, private val listener: (position: Int) -> Unit, private val listener2: (position: Int) -> Unit): RecyclerView.Adapter<ListMemberInRoomAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val removeButton : Button = view.findViewById(R.id.removeButton)
        val memberNameTextView : TextView = view.findViewById(R.id.memberNameTextView)
        val memberCardView: CardView = view.findViewById(R.id.memberCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_member_in_room, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.memberNameTextView.text = dataSet[position].user_name

        holder.removeButton.setOnClickListener {
            listener(position)
        }

        holder.memberCardView.setOnClickListener {
            listener2(position)
        }
    }
}