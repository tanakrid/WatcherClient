package com.example.watcherclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_show_rooms.*

class ShowRoomsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_rooms)

        val showRoomsFragment = ShowRoomsFragment()
        val showMembersFragment = ShowMembersFragment()
        val showDevicesFragment = ShowDevicesFragment()
        val showProfileFragment = UserProfileFragment()
        val showNotifyFragment = ShowNotifyFragment()
        setCurrentFragment(showRoomsFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homeMenu->{
                    titleNameTextView.text = "Rooms"
                    setCurrentFragment(showRoomsFragment)
                }
                R.id.membersMenu->{
                    titleNameTextView.text = "Members"
                    setCurrentFragment(showMembersFragment)
                }
                R.id.cameraMenu->{
                    titleNameTextView.text = "Devices"
                    setCurrentFragment(showDevicesFragment)
                }
                R.id.profileMenu->{
                    titleNameTextView.text = "Profile"
                    setCurrentFragment(showProfileFragment)
                }
                R.id.notificationMenu->{
                    titleNameTextView.text = "Notify"
                    setCurrentFragment(showNotifyFragment)
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment,fragment)
        commit()
    }
}