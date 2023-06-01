package com.example.metropolis_app.reservas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityReservasBinding
import com.example.metropolis_app.espacios.EspaciosAdapter
import com.example.metropolis_app.home.HomeActivity.Companion.PREFS_EMAIL

class ReservasActivity : AppCompatActivity() {
    lateinit var binding: ActivityReservasBinding
    private val reservasViewModel = ReservasViewModel()
    private lateinit var email: String

    // Creamos el adapter del recyclerView
    private val adapter = ReservasAdapter(emptyList(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_reservas)
        binding = ActivityReservasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email= getEmailFromSharedPreferences().toString()
        // Cargar el array y hacer la llamda a la API
        reservasViewModel.loadReservas(email)


        binding.rvReservas.adapter = adapter



        // Nos suscribimos al loading de viewModel
        reservasViewModel.reservas.observe(this) {
            adapter.reservas = it
            adapter.notifyDataSetChanged()
        }


    }

    private fun getEmailFromSharedPreferences(): String? {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString(PREFS_EMAIL, null)
    }
}