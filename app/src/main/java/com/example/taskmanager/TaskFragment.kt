package com.example.taskmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.data.model.Task

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            if (binding.inputTitle.text.toString().isNotEmpty()) {
                saveTask()
            }else{
                binding.inputTitle.error= "Заполните пустое поле"
            }
        }
    }

    private fun saveTask() {
        val data = Task(
           title= binding.inputTitle.text.toString(),
           desc =  binding.inputDesc.text.toString()
        )
      // setFragmentResult("fr_task", bundleOf("task" to data))
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }


}