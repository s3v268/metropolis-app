package com.example.metropolis_app.espacios

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ItemEspaciosBinding
import com.example.metropolis_app.models.Espacio


class EspaciosViewHolder(val view: View, val mContext: Context) : RecyclerView.ViewHolder(view) {

    private val binding = ItemEspaciosBinding.bind(view)

    fun bind(espacio: Espacio) {
        binding.tvNombre.text = espacio.nombre
        binding.tvArea.text = espacio.area.toString() + " m2"
        binding.tvCapacidad.text = espacio.capacidad.toString() + " personas"

        //glide
        Glide.with(mContext)
            .load(espacio.imagen_url)
            .centerCrop()
            .override(505, 505)
            .into(binding.imgEspacio)



    }
}