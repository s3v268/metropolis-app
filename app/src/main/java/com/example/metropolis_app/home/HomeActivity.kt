package com.example.metropolis_app.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityFormularioBinding
import com.example.metropolis_app.databinding.ActivityHomeBinding
import com.example.metropolis_app.databinding.ActivityMainBinding
import com.example.metropolis_app.espacios.EspaciosActivity
import com.example.metropolis_app.formulario.FormularioActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureView()
    }

    private fun configureView() {
        binding.homeBtnReservas.setOnClickListener{
            startActivity(Intent(this, FormularioActivity ::class.java))

        }
        binding.homeBtnEspacios.setOnClickListener{
            startActivity(Intent(this, EspaciosActivity ::class.java))
        }
    }
}