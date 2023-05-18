package com.example.metropolis_app.espacios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.metropolis_app.models.Espacio
import androidx.lifecycle.*
import com.example.metropolis_app.server.ApiConnection
import kotlinx.coroutines.launch

class EspaciosViewModel : ViewModel() {
    // El List de películas que devuelve la ApiRest
    private val _espacios = MutableLiveData<List<Espacio>>(emptyList())
    val espacios: LiveData<List<Espacio>> get() = _espacios

    fun loadEspacios() {
        viewModelScope.launch {
            var response = ApiConnection.service.listEspacios()
            if (response.isSuccessful) {
                _espacios.value = response.body()
            } else {
                _espacios.value = emptyList()
            }
        }
    }
}


