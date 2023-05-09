package com.example.metropolis_app

import com.example.metropolis_app.R
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import com.example.metropolis_app.databinding.ActivityFormularioBinding
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
        configureSpinner()
        configureSeekBar()
        configureDatePickerDialog()
    }


    private fun configureCheckbox(){

    }
    private fun configureSpinner() {
        val spinner = binding.spinner
        val items = arrayOf("--", "Sala A", "Sala B", "Sala C", "Padock", "Sala Conf. 1")
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

    fun configureDatePickerDialog() {
        binding.tvFechainicio.setOnClickListener{
            createDatePickerDialog(it as TextView)
        }
        binding.tvFechafinal.setOnClickListener{
            createDatePickerDialog(it as TextView)
        }
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


