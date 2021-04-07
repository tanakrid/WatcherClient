package com.example.watcherclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.watcherclient.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        submitButton.setOnClickListener{
            val intent = Intent(this, ShowRoomsActivity::class.java)
            val username =  usernameEditText.text
            val password =  passwordEditText.text

            // call login api

            startActivity(intent)
        }

        cancelButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}