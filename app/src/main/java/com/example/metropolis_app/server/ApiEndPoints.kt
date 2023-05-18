package com.example.metropolis_app.server

import com.example.metropolis_app.models.Espacio
import com.example.metropolis_app.models.Reserva
import okhttp3.RequestBody

import retrofit2.Response
import retrofit2.http.*

interface ApiEndPoints {
    @GET("cliente/espacios")
    suspend fun listEspacios(): Response <List<Espacio>>

    @POST("reservas/create")
    suspend fun enviarReserva(@Body reserva: Reserva) : Response<Array<String>>
}