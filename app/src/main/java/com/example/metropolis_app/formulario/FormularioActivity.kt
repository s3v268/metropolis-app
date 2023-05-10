package com.example.metropolis_app.formulario

import com.example.metropolis_app.R
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import com.example.metropolis_app.databinding.ActivityFormularioBinding
import com.example.metropolis_app.espacios.EspaciosActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar

class FormularioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormularioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureView()

    }

    private fun configureView() {
        configureForm()
        configureSpinner()
        configureSeekBar()
        configureCalendarView()
        configureMaterialCalendar()
    }

    private fun configureForm() {
        ed_changecolor_onfocus(binding.edFormEmail, R.color.primary_color, R.color.light_gray)
        ed_changecolor_onfocus(binding.edFormCompanyName, R.color.primary_color, R.color.light_gray)
    }

    private fun spinner_changecolor_onfocus(sp: Spinner, @ColorRes focusedColor: Int, @ColorRes unfocusedColor: Int){
        sp.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {

            } else {

            }
        }
    }
    private fun ed_changecolor_onfocus(ed: EditText, @ColorRes focusedColor: Int, @ColorRes unfocusedColor: Int){
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

        binding.selectDateBtn.setOnClickListener{
            picker.show(this.supportFragmentManager!!, picker.toString())
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


    private fun configureSpinner() {
        val spinner = binding.spinner
        val items = arrayOf("Selecciona un espacio", "Sala A", "Sala B", "Sala C", "Padock", "Sala Conf. 1")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun configureSeekBar() {
        binding.attendeesSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                val thumbRect = binding.attendeesSeekBar.thumb.bounds
                val thumbOffset = thumbRect.left + thumbRect.width() / 2
                binding.indicatorTextView.text = progress.toString()
                binding.indicatorTextView.x = binding.attendeesSeekBar.x + thumbOffset - binding.indicatorTextView.width / 2
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                binding.indicatorTextView.visibility = View.VISIBLE
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }


    private fun createDatePickerDialog( dateTv: TextView){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        //para quitar el spinner mode quitar el stylo de los parametros i las 3 lineas de abajo
        val datePickerDialog = DatePickerDialog(this, R.style.CustomDatePickerDialogTheme, { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            dateTv.text = date
        }, year, month, day)

        datePickerDialog.datePicker.calendarViewShown = false

        datePickerDialog.show()
    }

    fun onBusPassCheckboxClicked(view: View) {
        val busPassCheckbox = binding.busPassCheckbox
        val busPassContainer = binding.busPassContainer
        if (busPassCheckbox.isChecked) {
            // Checkbox is checked, show the associated views
            busPassContainer.visibility = View.VISIBLE
            // Add animation to show the container
            val animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_up)
            busPassContainer.startAnimation(animation)
        } else {
            // Checkbox is unchecked, hide the associated views
            busPassContainer.visibility = View.GONE
        }
        }
    }


