package com.example.taskmanager.ui.profile

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Notification.Action
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var selectedImg:Uri
    companion object{
        val IMAGE_REQUEST_CODE=1
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileImage.setOnClickListener {
            onClick()
        }
    }

    private fun onClick() {
        val intent = Intent()
        intent.action=Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (data.data != null){
                selectedImg=data.data!!
                binding.profileImage.setImageURI(selectedImg)


            }
        }
    }



}