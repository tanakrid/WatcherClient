package com.example.watcherclient

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.watcherclient.viewModel.ShowProfileFragmentViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFragment : Fragment() {

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(ShowProfileFragmentViewModel::class.java)
        val userID = "ZcD2T3FmtbLAIKDvRrVa"

        generateQRCode(userID)

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

    private fun generateQRCode(text: String){
        val width = 500
        val height = 500
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) { Log.d("tanakrid", "generateQRCode: ${e.message}") }
        imageView.setImageBitmap(bitmap)
    }

}