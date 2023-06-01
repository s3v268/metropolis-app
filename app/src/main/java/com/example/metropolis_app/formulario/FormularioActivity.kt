package com.example.metropolis_app.formulario

import com.example.metropolis_app.R
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.ColorRes
import com.example.metropolis_app.databinding.ActivityFormularioBinding
import com.example.metropolis_app.models.Espacio
import com.example.metropolis_app.models.Reserva
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class FormularioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormularioBinding
    private lateinit var viewModel: FormularioViewModel
    private lateinit var espacioSelected : Espacio
    private var arrayEventos : Array<String> = arrayOf("Selecciona un evento", "Formula 1 2023", "MotoGP 2023")
    private lateinit var startDate : String
    private lateinit var endDate : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        viewModel = FormularioViewModel()
        setContentView(binding.root)

        configureView()
    }

    private fun configureView() {
        //espacioSelected = intent.getParcelableExtra<Espacio>("espacio")!!
        configureForm()
        //configureSpinner(binding.reservasSpinnerEspacios, arrayOf(espacioSelected.nombre))
        configureSpinner(binding.reservasSpinnerEspacios, arrayOf("espacio"))
        configureSpinner(binding.reservasSpinnerEventos, arrayEventos)
        configureCalendarView()
        configureMaterialCalendar()
        configureNetwork()
        configureSubmitBtn()
    }

    private fun configureSubmitBtn() {
        binding.reservasBtnSubmit.setOnClickListener{
            viewModel.enviarReserva(getReserva())
        }
    }

    private fun getReserva() : Reserva {
        var event : String = arrayEventos[binding.reservasSpinnerEventos.selectedItemPosition]
        var email : String = binding.reservasEdEmail.text.toString()
        var companyName : String = binding.reservasEdCompanyname.text.toString()
        var n_attendees : Int = binding.reservasEdAsistentes.text.toString().toInt()
        var n_bus_passes : Int = binding.reservasEdBuspass.text.toString().toInt()
        var n_staff_pass : Int = binding.reservasEdStaffpass.text.toString().toInt()
        var n_parking_pass : Int = binding.reservasEdParkingpass.text.toString().toInt()
        var reserva = Reserva(
            event = event,
            email = email,
            company_name = companyName,
            space = "ghggh",
            startDate,
            endDate,
            n_attendees,
            n_bus_passes,
            n_staff_pass,
            n_parking_pass,
            "pendiente")
        return reserva
    }

    private fun checkForm() {
        val spinnerEventosValue : String? = null
        val spinnerEspaciosValue : String? = null

    }
        //if (binding.reservasSpinnerEventos.selectedItemPosition != 0)


    private fun configureNetwork() {
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                binding.reservasTvUserinfo.text = "Espera un moment, estem processant la teva resposta"
                binding.reservasTvUserinfo.visibility = View.VISIBLE

            } else{
                binding.reservasTvUserinfo.visibility = View.GONE
                if(viewModel.getRequestState()){
                    binding.reservasTvUserinfo.text = viewModel.getDetallesReserva()[1]
                    binding.reservasTvUserinfo.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun configureForm() {
        ed_changecolor_onfocus(binding.reservasEdEmail, R.color.primary_color, R.color.light_gray)
        ed_changecolor_onfocus(binding.reservasEdCompanyname, R.color.primary_color, R.color.light_gray)
    }

    private fun spinner_changecolor_onfocus(
        sp: Spinner,
        @ColorRes focusedColor: Int,
        @ColorRes unfocusedColor: Int
    ) {
        sp.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {

            } else {

            }
        }
    }

    private fun ed_changecolor_onfocus(
        ed: EditText,
        @ColorRes focusedColor: Int,
        @ColorRes unfocusedColor: Int
    ) {
        ed.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as EditText).compoundDrawablesRelative[0].setTint(getColor(focusedColor))
            } else {
                (view as EditText).compoundDrawablesRelative[0].setTint(getColor(unfocusedColor))
            }
        }
    }

    private fun configureMaterialCalendar() {
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        val now = Calendar.getInstance()
        builder.setSelection(androidx.core.util.Pair(now.timeInMillis, now.timeInMillis))
        builder.setTheme(R.style.CustomTheme)
        val picker = builder.build()



        binding.reservasLayoutDatepicker.setOnClickListener {
            picker.show(this.supportFragmentManager!!, picker.toString())
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            picker.addOnPositiveButtonClickListener { selection ->
                startDate = formatter.format(selection.first)
                endDate = formatter.format(selection.second)

                // Use the start and end dates as needed
            }
        }


    }

    private fun configureCalendarView() {
        /*
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        //calendarView.minDate = System.currentTimeMillis()
        calendarView.setDate(Calendar.getInstance().timeInMillis)
        // Disable May 6th
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            if (month == Calendar.MAY && dayOfMonth == 6) {
                Toast.makeText(this, "May 6th is disabled", Toast.LENGTH_SHORT).show()
                calendarView.setDate(Calendar.getInstance().timeInMillis) // Set the current date as selected
            }
        }*/
    }


    private fun configureSpinner(spinner: Spinner, items : Array<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }


    private fun createDatePickerDialog(dateTv: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        //para quitar el spinner mode quitar el stylo de los parametros i las 3 lineas de abajo
        val datePickerDialog = DatePickerDialog(
            this,
            R.style.CustomDatePickerDialogTheme,
            { _, year, month, dayOfMonth ->
                val date = "$dayOfMonth/${month + 1}/$year"
                dateTv.text = date
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.calendarViewShown = false

        datePickerDialog.show()
    }
}


