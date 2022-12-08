package com.example.taskmanager.ui.onBoarding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.google.firebase.auth.FirebaseAuth

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireActivity())
        val adapter = OnBoardingAdapter {
            if (FirebaseAuth.getInstance().currentUser?.uid == null) {
                findNavController().navigate(R.id.authFragment)
            } else {
                pref.saveShowBoarding(true)
                findNavController().navigateUp()
            }
        }
        binding.viewPager.adapter = adapter
    }


}


