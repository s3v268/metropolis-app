package com.example.metropolis_app.reservas

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.metropolis_app.databinding.ItemReservasBinding
import com.example.metropolis_app.models.ReservaV2

class ReservasViewHolder(val view: View, val mContext: Context) : RecyclerView.ViewHolder(view)  {

   private val binding = ItemReservasBinding.bind(view)

    fun bind(reserva: ReservaV2) {


    }

}


