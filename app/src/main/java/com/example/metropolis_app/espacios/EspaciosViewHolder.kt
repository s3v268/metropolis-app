package com.example.metropolis_app.espacios

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.metropolis_app.databinding.ItemEspaciosBinding
import com.example.metropolis_app.models.Espacio

class EspaciosViewHolder(val view: View, val mContext: Context) : RecyclerView.ViewHolder(view) {

    private val binding = ItemEspaciosBinding.bind(view)

    fun bind( espacio: Espacio) {


    }
}