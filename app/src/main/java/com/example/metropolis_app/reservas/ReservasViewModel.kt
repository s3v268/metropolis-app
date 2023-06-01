package com.example.metropolis_app.reservas

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metropolis_app.home.HomeActivity
import com.example.metropolis_app.models.ReservaV2
import com.example.metropolis_app.server.ApiConnection
import kotlinx.coroutines.launch

class ReservasViewModel: ViewModel() {
    // El List de reservas que devuelve la ApiRest
    private val _reservas = MutableLiveData<List<ReservaV2>>(emptyList())
    val reservas: LiveData<List<ReservaV2>> get() = _reservas

    private val _isLoading = MutableLiveData<Boolean>(false)
    public val isLoading: LiveData<Boolean> get() = _isLoading



    fun loadReservas(email: String) {
        _isLoading.value = true


        viewModelScope.launch {
            var response = ApiConnection.service.listReservas(email)
            if (response.isSuccessful) {
                _reservas.value = response.body()
            } else {
                _reservas.value = emptyList()
            }
        }

        _isLoading.value = false
    }

}



