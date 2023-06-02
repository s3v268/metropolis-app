package com.example.metropolis_app.formulario

import com.example.metropolis_app.R
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.ColorRes
import com.example.metropolis_app.databinding.ActivityFormularioBinding
import com.example.metropolis_app.home.HomeActivity
import com.example.metropolis_app.models.Espacio
import com.example.metropolis_app.models.Reserva
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar

class FormularioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormularioBinding
    private lateinit var viewModel: FormularioViewModel
    private lateinit var espacioSelected: Espacio
    private var arrayEventos: Array<String> =
        arrayOf("Selecciona un evento", "Formula 1 2023", "MotoGP 2023")
    private lateinit var startDate: String
    private lateinit var endDate: String
    private var bus_pass_n: String = "0"
    private var staff_pass_n: String = "0"
    private var parking_pass_n: String = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        viewModel = FormularioViewModel()
        setContentView(binding.root)

        configureView()
    }

    private fun configureView() {
        espacioSelected = intent.getParcelableExtra<Espacio>("espacio")!!

        //con esto pongo el correo en el campo email por defecto
        var email = getEmailFromSharedPreferences()
        binding.reservasEdEmail.setText(email)

        configureForm()
        //configureSpinner(binding.reservasSpinnerEspacios, arrayOf(espacioSelected.nombre))
        configureSpinner(binding.reservasSpinnerEspacios, arrayOf(espacioSelected.nombre))
        configureSpinner(binding.reservasSpinnerEventos, arrayEventos)
        configureCalendarView()
        configureMaterialCalendar()
        configureNetwork()
        configureSubmitBtn()
    }

    private fun abrirActivity() {

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

        // Finalizar la Activity actual
        finish()
    }
    private fun getEmailFromSharedPreferences(): String? {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString(HomeActivity.PREFS_EMAIL, null)
    }

    private fun configureSubmitBtn() {
        binding.reservasBtnSubmit.setOnClickListener {
            if (checkEvent()) {
                if (checkRequired()) {
                    checkOptional()
                    if (getReserva().n_attendees == 0) {
                        val snackbar = Snackbar.make(
                            binding.formularioParentlayout,
                            "El camp asistents ha de tenir un valor numèric superior a 1",
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.show()
                    } else {
                        viewModel.enviarReserva(getReserva())
                        //Redirecciono a la pantalla de inicio
                        abrirActivity()
                    }
                } else {
                    val snackbar = Snackbar.make(
                        binding.formularioParentlayout,
                        "No has omplert camps obligatoris",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.show()
                }

            } else {
                val snackbar = Snackbar.make(
                    binding.formularioParentlayout,
                    "No has seleccionat cap event",
                    Snackbar.LENGTH_LONG
                )
                snackbar.show()
            }

        }
    }

    private fun checkOptional(): Boolean {
        if (isStringNumeric(binding.reservasEdBuspass.text.toString())) {
            bus_pass_n = binding.reservasEdBuspass.text.toString()
            return true
        } else {
            return true
        }

        if (isStringNumeric(binding.reservasEdStaffpass.text.toString())) {
            staff_pass_n = binding.reservasEdStaffpass.text.toString()
            return true
        } else {
            return true
        }

        if (isStringNumeric(binding.reservasEdParkingpass.text.toString())) {
            parking_pass_n = binding.reservasEdParkingpass.text.toString()
            return true
        } else {
            return true
        }
    }

    private fun checkRequired(): Boolean {
        if (binding.reservasEdEmail.text.toString().isNullOrEmpty()) {
            binding.reservasEdEmail.setHintTextColor((getColor(R.color.primary_color)))
            return false
        } else if (binding.reservasEdCompanyname.text.toString().isNullOrEmpty()) {
            binding.reservasEdCompanyname.setHintTextColor((getColor(R.color.primary_color)))
            return false
        } else return true
    }

    fun isStringNumeric(input: String): Boolean {
        val numericRegex = Regex("^\\d+$")
        return numericRegex.matches(input)
    }

    private fun checkEvent(): Boolean {
        return binding.reservasSpinnerEventos.selectedItemPosition != 0
    }

    private fun getReserva(): Reserva {
        var event: String = arrayEventos[binding.reservasSpinnerEventos.selectedItemPosition]
        var email: String = binding.reservasEdEmail.text.toString()
        var companyName: String = binding.reservasEdCompanyname.text.toString()
        var attendees: String = binding.reservasEdAsistentes.text.toString()
        var n_attendees: Int
        if (isStringNumeric(attendees)) {
            n_attendees = attendees.toInt()
        } else {
            n_attendees = 0
        }

        var reserva = Reserva(
            event = event,
            email = email,
            company_name = companyName,
            space = espacioSelected.nombre,
            startDate,
            endDate,
            n_attendees,
            bus_pass_n.toInt(),
            staff_pass_n.toInt(),
            parking_pass_n.toInt(),
            "pendiente"
        )
        return reserva
    }

    private fun checkForm() {
        val spinnerEventosValue: String? = null
        val spinnerEspaciosValue: String? = null

    }
    //if (binding.reservasSpinnerEventos.selectedItemPosition != 0)


    private fun configureNetwork() {
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                binding.reservasTvUserinfo.text =
                    "Espera un moment, estem processant la teva resposta"
                binding.reservasTvUserinfo.visibility = View.VISIBLE

            } else {
                binding.reservasTvUserinfo.visibility = View.GONE
                if (viewModel.getRequestState()) {
                    binding.reservasTvUserinfo.text = viewModel.getDetallesReserva()[1]
                    binding.reservasTvUserinfo.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun configureForm() {
        ed_changecolor_onfocus(binding.reservasEdEmail, R.color.primary_color, R.color.light_gray)
        ed_changecolor_onfocus(
            binding.reservasEdCompanyname,
            R.color.primary_color,
            R.color.light_gray
        )
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
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            picker.addOnPositiveButtonClickListener { selection ->
                startDate = formatter.format(selection.first)
                endDate = formatter.format(selection.second)
                binding.reservasTvStartdate.text = startDate
                binding.reservasTvLastdate.text = endDate
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


    private fun configureSpinner(spinner: Spinner, items: Array<String>) {
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