package com.example.todolistapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botão: Adicionar Tarefa
        binding.buttonAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        // Botão: Minhas Tarefas
        binding.buttonListTasks.setOnClickListener {
            val intent = Intent(this, ListTaskActivity::class.java)
            startActivity(intent)
        }
    }
}