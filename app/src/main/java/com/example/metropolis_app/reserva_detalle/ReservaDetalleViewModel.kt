package com.example.metropolis_app.reserva_detalle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metropolis_app.models.ReservaV2
import com.example.metropolis_app.server.ApiConnection
import kotlinx.coroutines.launch

class ReservaDetalleViewModel: ViewModel(){
    fun cancelarReserva(reserva: ReservaV2) {

        viewModelScope.launch {
            //var response = ApiConnection.service.cancelarReserva(reserva.id)
            ApiConnection.service.cancelarReserva(reserva.id)
            /*if (response.isSuccessful) {
                reserva.estado = "Cancelada"
            } else {
                reserva.estado = "Error"
            }*/

        }

    }
}


/*    fun enviarReserva(reserva: Reserva) {
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
*/