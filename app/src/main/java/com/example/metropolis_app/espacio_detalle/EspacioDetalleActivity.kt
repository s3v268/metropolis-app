package com.example.metropolis_app.espacio_detalle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityEspacioDetalleBinding
import com.example.metropolis_app.formulario.FormularioActivity
import com.example.metropolis_app.models.Espacio

class EspacioDetalleActivity : AppCompatActivity() {

    private var _binding: ActivityEspacioDetalleBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_espacio_detalle)

        _binding = ActivityEspacioDetalleBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)

        val espacio = intent.getParcelableExtra<Espacio>("espacio")

        _binding!!.tvNombre.text = espacio?.nombre

        _binding!!.tvDescripcion.text =
            "¡Reserva ahora tu espacio en el circuito de Catalunya! Disponemos de un " +
                    "increíble lugar con capacidad para ${espacio?.capacidad} personas y un área " +
                    "de ${espacio?.area} metros cuadrados. ¡No pierdas la oportunidad y asegura tu reserva hoy mismo!"

        //glide
        Glide.with(this)
            .load(espacio?.imagen_url)

            .into(_binding!!.imgEspacio)


      _binding!!.btnReservar.setOnClickListener {
          openFormularioReserva(espacio!!)
      }

    }

    fun openFormularioReserva(espacio: Espacio ) {
        val intent = Intent(this, FormularioActivity::class.java)

        intent.putExtra("espacio",espacio)
        this.startActivity(intent)


    }
}