package com.example.todolistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.api.ApiService
import com.example.todolistapp.databinding.ActivityAddTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTaskName.text.toString()
            val description = binding.editDescription.text.toString()
            val date = binding.editTaskDate.text.toString()
            val time = binding.editTaskTime.text.toString()

            //val newTask = Task( name, description, date, time) // id = 0, backend vai gerar
            val newTask = Task(name = name, description = description, date = date, time = time, imageUrl = "https://via.placeholder.com/300")

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = ApiService.taskApi.addTask(newTask)
                    if (response.isSuccessful) {
                        println("Tarefa salva com sucesso.")
                        runOnUiThread { finish() } // Garantia de executar no Main Thread
                    } else {
                        println("Erro ao salvar tarefa: ${response.code()} - ${response.message()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("Exceção ao salvar tarefa: ${e.message}")
                }
            }

        }


        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }
}