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

    //Creamos el adapter del recyclerView
    private val espaciosAdapter: EspaciosAdapter = EspaciosAdapter(emptyList(), this)

    private lateinit var binding: ActivityEspaciosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_espacios)
        // Para trabajar con ViewBindig
        binding = ActivityEspaciosBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rvEspacios.adapter = espaciosAdapter

        // Cargamos los datos en el adapter
        espaciosAdapter.espacios = espacios
        espaciosAdapter.notifyDataSetChanged()


    }


}