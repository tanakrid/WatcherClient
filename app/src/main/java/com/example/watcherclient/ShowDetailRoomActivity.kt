package com.example.watcherclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcherclient.utility.ListDeviceAdapter
import com.example.watcherclient.utility.ListMemberAdapter
import com.example.watcherclient.viewModel.ShowDetailRoomActivityViewModel
import kotlinx.android.synthetic.main.activity_show_detail_room.*

class ShowDetailRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail_room)
        val viewModel = ViewModelProvider(this).get(ShowDetailRoomActivityViewModel::class.java)
        val userID = intent.getStringExtra("user_id")
        val roomID = intent.getStringExtra("room_id")

        deviceListRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
        }

        deviceListRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.show(userID, roomID)
        viewModel.myResponseRoom.observe(this, Observer {
            val room = it
            roomNameTextView.text = it.room_name
            val radioNumPerson: String = it.curr_num.toString() + '/' + it.limit_num.toString() + " person"
            numPersonTextView.text = radioNumPerson
            numWarnRateTextView.text = it.warn_num.toString()
            detailDescTextView.text = it.desc

            editRoomButton.setOnClickListener {
                val intent = Intent(this, CreateRoomPopupActivity::class.java)
                intent.putExtra("popuptitle", "Edit Room")
                intent.putExtra("darkstatusbar", false)
                intent.putExtra("user_id", userID)

                intent.putExtra("room_name", room.room_name)
                intent.putExtra("desc", room.desc)
                intent.putExtra("limit_num", room.limit_num)
                intent.putExtra("warn_num", room.warn_num)
                startActivity(intent)
            }

            deleteRoomButton.setOnClickListener{
                val intent = Intent(this, DeleteRoomPopupActivity::class.java)
                intent.putExtra("popuptitle", "Remove Room")
                intent.putExtra("darkstatusbar", false)

                intent.putExtra("room_id", roomID)
                intent.putExtra("user_id", userID)
                intent.putExtra("message", "Are you sure to remove " + room.room_name)
                startActivity(intent)
            }

        })

        viewModel.myResponseMemberList.observe(this, Observer {
            deviceListRecyclerview.adapter = ListMemberAdapter(it)
            if (it.isNotEmpty()){
                deviceListTextView.text = "Members"
            }
        })

        viewModel.myResponseDeviceList.observe(this, Observer {
            deviceListRecyclerview.adapter = ListDeviceAdapter(it)
            if (it.isNotEmpty()) {
                deviceTitleTextView.text = "Devices"
            }
        })
    }
}