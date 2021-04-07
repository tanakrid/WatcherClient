package com.example.watcherclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcherclient.utility.ListDeviceInRoomAdapter
import com.example.watcherclient.viewModel.ShowDeviceInRoomActivityViewModel
import kotlinx.android.synthetic.main.activity_manage_device_in_room.*

class ManageDeviceInRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_device_in_room)
        val viewModel = ViewModelProvider(this).get(ShowDeviceInRoomActivityViewModel::class.java)
        val userID = intent.getStringExtra("user_id")
        val roomID = intent.getStringExtra("room_id")
        val roomName = intent.getStringExtra("room_name")

        deviceListRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.showDevice(userID, roomID)
        viewModel.myResponseList.observe(this, Observer {
            val data = it
            deviceListRecyclerview.adapter = ListDeviceInRoomAdapter(it, {
                val intent = Intent(this, DeleteDevicePopupActivity::class.java)
                intent.putExtra("popuptitle", "Remove Room")
                intent.putExtra("darkstatusbar", false)

                intent.putExtra("room_id", roomID)
                intent.putExtra("user_id", userID)
                intent.putExtra("device_id", data[it].device_id)
                intent.putExtra("message", "Are you sure to remove $roomName")
                startActivity(intent)
            })

            if (it.isNotEmpty()){
                deviceListTextView.text = "Devices list"
            }
        })
    }
}