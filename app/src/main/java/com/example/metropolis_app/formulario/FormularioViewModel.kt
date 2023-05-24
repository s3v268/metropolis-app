package com.example.metropolis_app.formulario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metropolis_app.models.Reserva
import com.example.metropolis_app.server.ApiConnection
import kotlinx.coroutines.launch

class FormularioViewModel : ViewModel() {
    private var detalles_reserva = emptyArray<String>()
    private var requestCompleted = false
    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading


    fun enviarReserva(reserva: Reserva) {
        viewModelScope.launch {
            _loading.value = true
            requestCompleted = false
            var response = ApiConnection.service.enviarReserva(reserva)
            if (response.isSuccessful) {
                detalles_reserva = response.body() ?: emptyArray()
            } else {
                detalles_reserva = emptyArray()
            }
            _loading.value = false
            requestCompleted = true
        }
    }

    fun getDetallesReserva(): Array<String> {
        return detalles_reserva
    }
    fun getRequestState(): Boolean {
        return requestCompleted
    }
}