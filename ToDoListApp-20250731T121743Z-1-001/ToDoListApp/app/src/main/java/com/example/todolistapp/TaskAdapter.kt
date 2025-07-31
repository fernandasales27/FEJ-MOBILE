package com.example.todolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TaskAdapter(
    private val tasks: List<Task>,
    private val onDeleteClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.textTaskName)
        val description: TextView = itemView.findViewById(R.id.textDescription)
        val taskDate: TextView = itemView.findViewById(R.id.textTaskDate)
        val taskTime: TextView = itemView.findViewById(R.id.textTaskTime)
        val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)
        val taskImage: ImageView = itemView.findViewById(R.id.imageTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskName.text = task.name
        holder.description.text = task.description
        holder.taskDate.text = task.date
        holder.taskTime.text = task.time
        holder.buttonDelete.setOnClickListener {
            onDeleteClick(task)



        }
        Picasso.get()
            .load(task.imageUrl)
            .placeholder(R.drawable.loading)
            .into(holder.taskImage)


    }

    override fun getItemCount() = tasks.size
}