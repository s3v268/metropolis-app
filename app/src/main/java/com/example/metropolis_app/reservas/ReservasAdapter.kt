package com.example.metropolis_app.reservas

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.metropolis_app.R
import com.example.metropolis_app.models.Espacio
import com.example.metropolis_app.models.ReservaV2

class ReservasAdapter (
    var reservas: List<ReservaV2> = emptyList(),
    private val mContext: Context,
    private val onItemClicked: (ReservaV2) -> Unit

) : RecyclerView.Adapter<ReservasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReservasViewHolder(
            layoutInflater.inflate(R.layout.item_reservas, parent, false),
            mContext
        )
    }

    override fun onBindViewHolder(viewHolder: ReservasViewHolder, position: Int) {

        val reserva = reservas[position]
        viewHolder.bind(reserva)

        //para capturar el click en el elemento
        viewHolder.itemView.setOnClickListener { onItemClicked(reserva) }


    }

    override fun getItemCount(): Int = reservas.size
}





