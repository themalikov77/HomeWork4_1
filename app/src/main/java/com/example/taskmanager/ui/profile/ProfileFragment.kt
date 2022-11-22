package com.example.taskmanager.ui.profile


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    private val getContent: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            Glide.with(this).load(imageUri).into(binding.profileImage)
            pref.saveImage(imageUri.toString())
        }

    companion object {
        val TYPE = "image/*"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileImage.setOnClickListener {
            getContent.launch(TYPE)

        }
        pref = Pref(requireContext())
        binding.outlinedEditText.setText(pref.getName())
        binding.etAge.setText(pref.getAge())
        Glide.with(this).load(pref.getImage()).into(binding.profileImage)
        binding.outlinedEditText.addTextChangedListener {
            pref.savedText(binding.outlinedEditText.text.toString())
            pref.saveAge(binding.etAge.text.toString())

        }


    }
}


