package com.example.taskmanager.ui.onBoarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import com.example.taskmanager.ui.home.HomeFragment
import com.example.taskmanager.ui.model.OnBoard
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = OnBoardingAdapter(this::onClick)
        binding.viewPager.adapter = adapter


    }
    private fun onClick(view: View){
        findNavController().navigateUp()
    }


}


