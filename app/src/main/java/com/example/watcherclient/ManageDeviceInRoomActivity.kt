package com.example.watcherclient

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcherclient.utility.ListDeviceInRoomAdapter
import com.example.watcherclient.viewModel.ShowDeviceInRoomActivityViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import kotlinx.android.synthetic.main.activity_manage_device_in_room.*

class ManageDeviceInRoomActivity : AppCompatActivity() {

    private lateinit var viewModel: ShowDeviceInRoomActivityViewModel
    private lateinit var userID: String
    private lateinit var roomID: String
    private lateinit var roomName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_device_in_room)
        viewModel = ViewModelProvider(this).get(ShowDeviceInRoomActivityViewModel::class.java)
        userID = intent.getStringExtra("user_id")
        roomID = intent.getStringExtra("room_id")
        roomName = intent.getStringExtra("room_name")

        titleNameTextView.text = roomName

        deviceListRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
        }

        scanQRcodeDeviceButton.setOnClickListener{
            scanQRCode()
        }

        viewModel.showDevice(userID, roomID)
        viewModel.myResponseList.observe(this, Observer {
            val data = it
            deviceListRecyclerview.adapter = ListDeviceInRoomAdapter(it){
                val intent = Intent(this, DeleteDevicePopupActivity::class.java)
                intent.putExtra("popuptitle", "Remove Room")
                intent.putExtra("darkstatusbar", false)

                intent.putExtra("room_id", roomID)
                intent.putExtra("user_id", userID)
                intent.putExtra("device_id", data[it].device_id)
                intent.putExtra("message", "Are you sure to remove ${data[it].device_id}")
                startActivity(intent)
            }

            if (it.isNotEmpty()){
                deviceListTextView.text = "Devices list"
            }
        })
    }

    private fun scanQRCode(){
        val integrator = IntentIntegrator(this).apply {
            captureActivity = CaptureActivity::class.java
            setOrientationLocked(false)
            setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            setPrompt("Scanning Code")
        }
        integrator.initiateScan()
    }

    // Get the results:
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addDevice(userID, roomID, result.contents)
                Toast.makeText(this, "Add device(${result.contents}) complete", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}