package com.example.todolistapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.api.ApiService
import com.example.todolistapp.databinding.ActivityListTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewTasks.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        // ⚠️ Chamada para a API real
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val tasks = ApiService.taskApi.getTasks()
                runOnUiThread {
                    binding.recyclerViewTasks.adapter = TaskAdapter(tasks) { taskToDelete ->
                        deleteTask(taskToDelete)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun deleteTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiService.taskApi.deleteTask(task.id!!)
                if (response.isSuccessful) {
                    runOnUiThread {
                        Toast.makeText(this@ListTaskActivity, "Tarefa removida", Toast.LENGTH_SHORT).show()
                        recreate() // força reload da tela
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@ListTaskActivity, "Erro ao deletar: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@ListTaskActivity, "Erro de conexão", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}