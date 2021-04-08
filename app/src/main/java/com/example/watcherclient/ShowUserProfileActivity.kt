package com.example.watcherclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.watcherclient.viewModel.ShowProfileFragmentViewModel
import kotlinx.android.synthetic.main.activity_show_user_profile.*

class ShowUserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user_profile)

        val bundle = intent.extras
        val viewModel = ViewModelProvider(this).get(ShowProfileFragmentViewModel::class.java)
        val userID = bundle?.getString("user_id", "")

        if (userID != null) {
            viewModel.showUser(userID)
            viewModel.myProfile.observe(this, Observer {
                usernameTextView.text = it.user_name
                emailTextView.text = it.email
            })
        }


        if (userID != null) {
            viewModel.getNumHostedRoom(userID)
            viewModel.myHostRoomsID.observe(this, Observer {
                hostedRoomTextView.text = "${it.size} Unit"
            })

            viewModel.myMemberRoomsID.observe(this, Observer{
                membersRoomTextView.text = "${it.size} Unit"
            })
        }
    }
}