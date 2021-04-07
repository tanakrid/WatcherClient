package com.example.watcherclient

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcherclient.utility.ListMemberRoomsAdapter
import com.example.watcherclient.viewModel.ShowMemberRoomFragmentViewModel
import kotlinx.android.synthetic.main.fragment_show_members.*

class ShowMembersFragment : Fragment() {

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(ShowMemberRoomFragmentViewModel::class.java)
        val userID = "ZcD2T3FmtbLAIKDvRrVa"

        memberRoomRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.showMember(userID)
        viewModel.myResponseList.observe(this, Observer {
            val data = it
            memberRoomRecyclerView.adapter = ListMemberRoomsAdapter(it) {
                val intent = Intent(activity, ManageMemberInRoomActivity()::class.java)
                val key = data.keys.toList()[it]
                intent.putExtra("room_id", key)
                intent.putExtra("user_id", userID)
                intent.putExtra("room_name", data[key]?.room_name)
                startActivity(intent)
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_members, container, false)
    }
}