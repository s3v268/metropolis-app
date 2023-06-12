package com.example.metropolis_app.reserva_detalle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityReservaDetalleBinding
import com.example.metropolis_app.espacios.EspaciosViewModel
import com.example.metropolis_app.models.Espacio
import com.example.metropolis_app.models.ReservaV2

class ReservaDetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservaDetalleBinding
    private val reservaDetalleViewModel: ReservaDetalleViewModel = ReservaDetalleViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_reserva_detalle)
        binding = ActivityReservaDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reserva = intent.getParcelableExtra<ReservaV2>("reserva")

        binding.tvReservaEvent.text= "Evento: ${reserva?.event}"
        binding.tvReservaFecha.text= "Fecha de la solicitud: ${reserva?.startDate?.substring(0, Math.min(reserva?.startDate!!.length, 10))}"
        binding.tvReservaEmail.text= "Email: ${reserva?.email}"
        binding.tvReservaCompany.text= "Compañia: ${reserva?.companyName}"
        binding.tvReservaSpace.text= "Espacio: ${reserva?.space}"
        binding.tvReservaPases.text= "Assistants pass: ${reserva?.nAttendees}"+
                "\nStaff pass: ${reserva?.nStaffPass}"+
                "\nBus pass: ${reserva?.nBusPass}"+
                "\nParking pass: ${reserva?.nAttendees}"


        var fechaDesde = reserva?.startDate?.substring(0, Math.min(reserva?.startDate!!.length, 10))
        var fechaHasta = reserva?.endDate?.substring(0, Math.min(reserva?.endDate!!.length, 10))



        binding.tvRservaDuracion.text= "Desde: ${fechaDesde} \nHasta: ${fechaHasta}"

        binding.tvReservaEstado.text= "Estado: ${reserva?.accepted?.uppercase()}"
        setColorState(reserva?.accepted.toString())

        binding.btnCancelar.setOnClickListener {
            reservaDetalleViewModel.cancelarReserva(reserva!!)
            finish()

        }


    }

    private fun setColorState(state:String){

        val greenColor = ContextCompat.getColor(this, R.color.teal_700)
        if (state.equals("Aceptada")) {
            binding.tvReservaEstado.setTextColor(greenColor)
        }
        if (state.equals("Denegada") || state.equals("Cancelada")) {
            binding.tvReservaEstado.setTextColor(Color.RED)
        }
        if (state.equals("Pendiente")) {
            binding.tvReservaEstado.setTextColor(Color.BLACK)
        }
    }
}