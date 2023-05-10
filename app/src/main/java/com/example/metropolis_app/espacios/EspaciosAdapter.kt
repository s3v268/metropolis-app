package com.example.metropolis_app.espacios

import android.content.Context
import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.metropolis_app.R
import com.example.metropolis_app.models.Espacio

 class EspaciosAdapter(
    var espacios: List<Espacio> = emptyList(),
    private val mContext: Context,/*
    private val onItemClicked: (Espacio) -> Unit*/
) : RecyclerView.Adapter<EspaciosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspaciosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EspaciosViewHolder(
            layoutInflater.inflate(R.layout.item_espacios, parent, false),
            mContext
        )
    }

    override fun onBindViewHolder(viewHolder: EspaciosViewHolder, position: Int) {

        val espacio = espacios[position]
        viewHolder.bind(espacio)


        //para capturar el click en el elemento
        //viewHolder.itemView.setOnClickListener { onItemClicked(espacio) }

    }

    override fun getItemCount(): Int = espacios.size

}