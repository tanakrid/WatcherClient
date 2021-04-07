package com.example.watcherclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.watcherclient.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        submitButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            val username =  usernameEditText.text.toString()
            val password =  passwordEditText.text.toString()
            val rePassword = rePasswordEditText.text.toString()
            val email = emailEditText.text.toString()

            if (password == rePassword) {
                // call sign up api
                viewModel.signup(username, email, password)
                Toast.makeText(this, "Sign up complete", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }else{
                usernameEditText.setText("")
                passwordEditText.setText("")
                rePasswordEditText.setText("")
                emailEditText.setText("")
                Toast.makeText(this, "repeated password not correct!", Toast.LENGTH_SHORT).show()
            }
        }

        cancelButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}