package com.example.metropolis_app.espacios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.metropolis_app.databinding.ActivityEspaciosBinding
import com.example.metropolis_app.models.Espacio
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class EspaciosViewModel: ViewModel() {
    // El List de películas que devuelve la ApiRest
    private val _espacio = MutableLiveData<List<Espacio>>(emptyList())
    val espacio: LiveData<List<Espacio>> get() = _espacio

    fun loadEspacios() {
        viewModelScope.launch {


        }

    }
}


