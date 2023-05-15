package com.example.metropolis_app.espacios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityEspaciosBinding
import com.example.metropolis_app.models.Espacio

import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class EspaciosActivity : AppCompatActivity() {
    private var espacios: MutableList<Espacio> = ArrayList()
    private val espaciosViewModel: EspaciosViewModel = EspaciosViewModel()
    private lateinit var binding: ActivityEspaciosBinding

    // Creamos el adapter del recyclerView
    private val adapter = EspaciosAdapter(emptyList(),this)

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


}