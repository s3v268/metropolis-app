package com.example.metropolis_app.espacio_detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityEspacioDetalleBinding

class EspacioDetalleActivity : AppCompatActivity() {

    private var _binding: ActivityEspacioDetalleBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_espacio_detalle)

        _binding = ActivityEspacioDetalleBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)


    }
}