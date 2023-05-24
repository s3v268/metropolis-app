package com.example.metropolis_app.espacios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityEspaciosBinding
import com.example.metropolis_app.espacio_detalle.EspacioDetalleActivity
import com.example.metropolis_app.models.Espacio
import com.google.android.material.snackbar.Snackbar

import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class EspaciosActivity : AppCompatActivity() {
    private var espacios: MutableList<Espacio> = ArrayList()
    private val espaciosViewModel: EspaciosViewModel = EspaciosViewModel()
    private lateinit var binding: ActivityEspaciosBinding

    // Creamos el adapter del recyclerView
    private val adapter = EspaciosAdapter(emptyList(),this) {onItemClicked(it)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_espacios)
        // Para trabajar con ViewBindig
        binding = ActivityEspaciosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Cargar el array y hacer la llamda a la API
        espaciosViewModel.loadEspacios()

        binding.rvEspacios.adapter = adapter



        // Nos suscribimos al loading de viewModel
        espaciosViewModel.espacios.observe(this) {
            adapter.espacios = it
            adapter.notifyDataSetChanged()
        }

    }


    private fun onItemClicked(it: Espacio) {
        Snackbar.make(binding.root, "Espacio ${it.nombre}", Snackbar.LENGTH_LONG).show()


        val intent = Intent(this, EspacioDetalleActivity::class.java)
        intent.putExtra("espacio", it)
        startActivity(intent)


    }


}