package com.example.watcherclient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcherclient.utility.ListRoomsAdapter
import com.example.watcherclient.viewModel.ShowRoomsActivityViewModel
import kotlinx.android.synthetic.main.fragment_show_rooms.*

class ShowRoomsFragment : Fragment() {

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(ShowRoomsActivityViewModel::class.java)
        val userID = "ZcD2T3FmtbLAIKDvRrVa"

        RoomsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.showAll(userID)
        viewModel.myResponseList.observe(this, Observer {
            RoomsRecyclerView.adapter = ListRoomsAdapter(it) {
                val intent = Intent(activity, ShowDetailRoomActivity()::class.java)
                val key = it.keys.toList()[0]
                intent.putExtra("room_id", key)
                intent.putExtra("user_id", userID)


                intent.putExtra("room_name", it[key]?.room_name)
                intent.putExtra("desc", it[key]?.desc)
                intent.putExtra("limit_num", it[key]?.limit_num)
                intent.putExtra("warn_num", it[key]?.warn_num)

                startActivity(intent)
            }
        })

        fab.setOnClickListener { view ->
            val intent = Intent(activity, CreateRoomPopupActivity::class.java)
            intent.putExtra("popuptitle", "Create Room")
            intent.putExtra("darkstatusbar", false)
            intent.putExtra("user_id", userID)
            intent.putExtra("isCreate", true)
            startActivity(intent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_rooms, container, false)
    }
}