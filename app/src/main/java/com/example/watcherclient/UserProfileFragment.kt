package com.example.watcherclient

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcherclient.utility.ListRoomsAdapter
import com.example.watcherclient.viewModel.ShowProfileFragmentViewModel
import kotlinx.android.synthetic.main.fragment_show_rooms.*
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFragment : Fragment() {

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(ShowProfileFragmentViewModel::class.java)
        val userID = "ZcD2T3FmtbLAIKDvRrVa"

        viewModel.showUser(userID)
        viewModel.myProfile.observe(this, Observer {
            usernameTextView.text = it.user_name
            emailTextView.text = it.email
        })

        viewModel.getNumHostedRoom(userID)
        viewModel.myHostRoomsID.observe(this, Observer {
            hostedRoomTextView.text = "${it.size} Unit"
        })

        viewModel.myMemberRoomsID.observe(this, Observer{
            membersRoomTextView.text = "${it.size} Unit"
        })

        logoutButton.setOnClickListener {

        }

        changePasswordButton.setOnClickListener {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

}