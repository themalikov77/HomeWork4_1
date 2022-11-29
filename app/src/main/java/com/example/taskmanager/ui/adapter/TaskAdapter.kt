package com.example.taskmanager.ui.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.data.model.Task

class TaskAdapter(
    private val tasks: ArrayList<Task> = arrayListOf(),
    private var selected: Int = -1,
    private val onClick: (Task) -> Unit,
    val context: Context,
    val activity: FragmentActivity?
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])


    }

    @SuppressLint("NotifyDataSetChanged")
    fun addTask(newTask: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(newTask)
        notifyDataSetChanged()
    }


    fun addTasks(task: Task) {
        tasks.add(0, task)
        notifyItemChanged(0)
    }


    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            if (adapterPosition % 2 == 0) {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
                binding.tvDesc.setTextColor(ContextCompat.getColor(context, R.color.white))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                binding.tvDesc.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            itemView.setOnLongClickListener {
                onLong(task)
                return@setOnLongClickListener true
            }
            binding.root.setOnClickListener {
                onClick(task)
            }

        }

        private fun onLong(task: Task) {
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Delete?")
            alertDialog.setPositiveButton("Ok", DialogInterface.OnClickListener { _, _ ->
                App.db.taskDao().delete(task)
                activity?.recreate()
            })
            alertDialog.setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { _, _ ->

                })
            alertDialog.show()
        }


    }


}
