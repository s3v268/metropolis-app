package com.example.metropolis_app.reservas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.metropolis_app.databinding.ActivityReservasBinding
import com.example.metropolis_app.home.HomeActivity.Companion.PREFS_EMAIL
import com.example.metropolis_app.models.ReservaV2
import com.example.metropolis_app.reserva_detalle.ReservaDetalleActivity

class ReservasActivity : AppCompatActivity() {
    lateinit var binding: ActivityReservasBinding
    private val reservasViewModel = ReservasViewModel()
    private lateinit var email: String

    // Creamos el adapter del recyclerView
    private val adapter = ReservasAdapter(emptyList(), this)  {onItemClicked(it)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_reservas)
        binding = ActivityReservasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = getEmailFromSharedPreferences().toString()
        // Cargar el array y hacer la llamda a la API
        reservasViewModel.loadReservas(email)


        binding.rvReservas.adapter = adapter


        // Nos suscribimos al loading de viewModel
        reservasViewModel.reservas.observe(this) {
            adapter.reservas = it
            adapter.notifyDataSetChanged()
        }



        reservasViewModel.isLoading.observe(this) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.imgRefresh.setOnClickListener {
            reservasViewModel.loadReservas(email)
            adapter.notifyDataSetChanged()
        }



        /*reservasViewModel.reservas.observe(this) {
            if (reservasViewModel.reservas.value?.size == 0) {
                binding.tvNoReservas.text = "No tienes reservas aun"
            } else {
                binding.tvNoReservas.visibility = View.GONE
            }

        }*/


    }

    private fun getEmailFromSharedPreferences(): String? {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString(PREFS_EMAIL, null)
    }

    private fun onItemClicked(it: ReservaV2) {

        val intent = Intent(this, ReservaDetalleActivity::class.java)
        intent.putExtra("reserva", it)
        startActivity(intent)


    }


    override fun onResume() {
        super.onResume()
        reservasViewModel.loadReservas(email)
        adapter.notifyDataSetChanged()
    }
}