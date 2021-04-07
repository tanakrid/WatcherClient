package com.example.watcherclient.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.watcherclient.R
import com.example.watcherclient.model.Person

class ListMemberAdapter(private val dataSet: List<Person>): RecyclerView.Adapter<ListMemberAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        val memberNameTextView: TextView = view.findViewById(R.id.memberNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_member, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.memberNameTextView.text = dataSet[position].user_name
    }
}