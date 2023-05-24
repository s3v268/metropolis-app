package com.example.metropolis_app.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityFormularioBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormularioBinding
    private lateinit var viewModel: HomeViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}