package com.example.metropolis_app.server

import com.example.metropolis_app.models.Espacio
import com.example.metropolis_app.models.Reserva
import com.example.metropolis_app.models.ReservaV2

import retrofit2.Response
import retrofit2.http.*

interface ApiEndPoints {
    @GET("espacios")
    suspend fun listEspacios(): Response <List<Espacio>>

    @POST("reservas")
    suspend fun enviarReserva(@Body reserva: Reserva) : Response<Array<String>>

    //@GET("reservas")
    //suspend fun listReservas(@Query("email") email: String): Response<List<Reserva>>

    @GET("reservas/{email}")
    suspend fun listReservas(@Path("email") email: String): Response<List<ReservaV2>>

    @PUT("reservas/{id}/cancel")
    suspend fun cancelarReserva(@Path("id") id: Int): Response<Response<Void>>
}