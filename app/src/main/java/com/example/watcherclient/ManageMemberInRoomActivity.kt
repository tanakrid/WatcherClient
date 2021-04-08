package com.example.watcherclient

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcherclient.utility.ListMemberInRoomAdapter
import com.example.watcherclient.viewModel.ShowMemberInRoomActivityViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import kotlinx.android.synthetic.main.activity_manage_member_in_room.*

class ManageMemberInRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_member_in_room)
        val viewModel = ViewModelProvider(this).get(ShowMemberInRoomActivityViewModel::class.java)
        val userID = intent.getStringExtra("user_id")
        val roomID = intent.getStringExtra("room_id")
        val roomName = intent.getStringExtra("room_name")

        titleNameTextView.text = roomName

        memberListRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.showMember(userID, roomID)
        viewModel.myResponseList.observe(this, Observer {
            val data = it
            memberListRecyclerview.adapter = ListMemberInRoomAdapter(it, {
                val intent = Intent(this, DeleteMemberPopupActivity::class.java)
                intent.putExtra("popuptitle", "Remove Room")
                intent.putExtra("darkstatusbar", false)

                intent.putExtra("room_id", roomID)
                intent.putExtra("user_id", userID)
                intent.putExtra("member_id", data[it].user_id)
                intent.putExtra("message", "Are you sure to remove $roomName")
                startActivity(intent)
            },{
                val intent = Intent(this, ShowUserProfileActivity::class.java)
                intent.putExtra("user_id", data[it].user_id)
                startActivity(intent)
            })

            if (it.isNotEmpty()){
                deviceListTextView.text = "Members list"
            }
        })

        submitButton.setOnClickListener {
            val user_id: String = editTextTextPersonName.text.toString()
            viewModel.addMember(userID, roomID, user_id)
            Toast.makeText(this, "Add member id $user_id complete", Toast.LENGTH_LONG).show()
        }

        scanQRcodeButton.setOnClickListener {
            scanQRCode()
        }

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
                editTextTextPersonName.setText(result.contents)
                Toast.makeText(this, "Add device(${result.contents}) complete", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}