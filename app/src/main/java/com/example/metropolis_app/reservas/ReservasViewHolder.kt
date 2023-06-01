package com.example.metropolis_app.reservas

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
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

        if (reserva.accepted.equals("Aceptada")) {
            binding.tvEstado.setTextColor(Color.GREEN); // Set text color to green
        } else {
            binding.tvEstado.setTextColor(Color.BLACK); // Set text color to black
        }

        binding.tvEstado.setText(reserva.accepted); // Set the text

    }
}


