package com.example.metropolis_app.reservas

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ItemReservasBinding
import com.example.metropolis_app.models.ReservaV2

class ReservasViewHolder(val view: View, val mContext: Context) : RecyclerView.ViewHolder(view) {

    private val binding = ItemReservasBinding.bind(view)

    fun bind(reserva: ReservaV2) {

        binding.tvEvent.text = reserva.event
        val createdAt = reserva.createdAt
        val truncatedCreatedAt = createdAt.substring(0, Math.min(createdAt.length, 10))
        binding.tvFecha.text = "Solicitud $truncatedCreatedAt"
        binding.tvNombre.text = reserva.space
        binding.tvEstado.setText(reserva.accepted.uppercase()); // Set the text
        val redColor = ContextCompat.getColor(mContext, R.color.primary_color)
        val greenColor = ContextCompat.getColor(mContext, R.color.teal_700)

        if (reserva.accepted.equals("Aceptada")) {
            binding.itemReserva.setBackgroundColor(greenColor); // Set background color to green        }




        }
        if (reserva.accepted.equals("Denegada")) {
            binding.itemReserva.setBackgroundColor( redColor); // Set background color to red
        }
    }
}

