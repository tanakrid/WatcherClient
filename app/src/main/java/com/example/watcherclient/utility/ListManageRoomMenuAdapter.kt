package com.example.watcherclient.utility

import android.content.Context
import android.content.Intent
import android.graphics.Color.BLUE
import android.hardware.camera2.params.RggbChannelVector.BLUE
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.watcherclient.CreateRoomPopupActivity
import com.example.watcherclient.R
import com.example.watcherclient.model.FilterItem
import com.example.watcherclient.model.Room

class ListManageRoomMenuAdapter(val context: Context, private val room: Room, private val listener: () -> Unit) : RecyclerView.Adapter<ListManageRoomMenuAdapter.MyViewHolder>() {

    var filerList : List<FilterItem> = mutableListOf()

    private var selectedItem: Int = -1
    var callback: RecyclerviewCallbacks<FilterItem>? = null

    fun addAlertFilter(filers: List<FilterItem>) {
        filerList = filers.toMutableList()
        notifyDataSetChanged()
    }

    fun selectedItem(position: Int){
        selectedItem = position
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {
        val item = filerList[p1]
        holder.tvName.text = item.name
        holder.alert_filter_icon.background = ContextCompat.getDrawable(context, item.icon)

        if(p1 == selectedItem) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.alert_filter_icon.backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorBackground)
            }
            holder.tvName.setTextColor(ContextCompat.getColor(context, R.color.colorBackground))
            holder.alert_filter_selected.visibility = View.VISIBLE
        } else {
            holder.alert_filter_selected.visibility = View.INVISIBLE
        }

//        if (item.name == "Edit"){
//            holder.filterLayout.setOnClickListener {
//                val intent = Intent()
////                intent.putExtra("popuptitle", "Create Room")
////                intent.putExtra("darkstatusbar", false)
////                intent.putExtra("user_id", userID)
////                startActivity(intent)
//            }
//        }else if (item.name == "Delete"){
//
//        }
    }

    fun setOnClick(click: RecyclerviewCallbacks<FilterItem>){
        callback = click
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.card_manage_room_menu,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filerList.size
    }

    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var tvName: TextView = itemView.findViewById(R.id.alert_filter_name)
        var alert_filter_icon: ImageView = itemView.findViewById(R.id.alert_filter_icon)
        var alert_filter_selected: ImageView = itemView.findViewById(R.id.alert_filter_selected)
        var filterLayout: ConstraintLayout = itemView.findViewById(R.id.alert_filter_item_layout)
        init {
            setClickListener(filterLayout)
        }

        private fun setClickListener(view: View) {
            view.setOnClickListener {
                callback?.onItemClick(it, adapterPosition, filerList[adapterPosition])
            }
        }
    }

}