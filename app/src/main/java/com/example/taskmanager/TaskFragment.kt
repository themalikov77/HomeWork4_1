package com.example.taskmanager

import android.annotation.SuppressLint
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
    private var task: Task? = null
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
        arguments?.let {
            val value = it.getSerializable("task")
            if (value != null) {
                task = value as Task
                binding.inputTitle.setText(task?.title.toString())
                binding.inputDesc.setText(task?.desc.toString())
                if (task != null) {
                    binding.btnSave.text = "Update"
                } else {
                    binding.btnSave.text = "Save"
                }
            }
        }

        binding.btnSave.setOnClickListener {

            if (binding.inputTitle.text.toString().isNotEmpty()) {
                if (task != null) {
                    updateTask()

                } else {
                    saveTask()
                }
            } else {
                binding.inputTitle.error = "Заполните пустое поле"
            }
        }
    }

    private fun updateTask() {
        task?.title = binding.inputTitle.toString()
        task?.desc = binding.inputDesc.toString()
        task?.let { App.db.taskDao().update(it) }
        findNavController().navigateUp()
    }

    private fun saveTask() {
        val data = Task(
            title = binding.inputTitle.text.toString(),
            desc = binding.inputDesc.text.toString()
        )
        // setFragmentResult("fr_task", bundleOf("task" to data))
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }


}