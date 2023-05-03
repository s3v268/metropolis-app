package com.example.metropolis_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.metropolis_app.databinding.ActivityMainBinding
import com.example.metropolis_app.espacios.EspaciosActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEspacios.setOnClickListener{
            goTo(Intent(this, EspaciosActivity ::class.java))
        }
        binding.btnReservas.setOnClickListener{
            goTo(Intent(this, FormularioActivity ::class.java))
        }
    }

    private fun goTo(intent: Intent){
        startActivity(intent)
    }
}