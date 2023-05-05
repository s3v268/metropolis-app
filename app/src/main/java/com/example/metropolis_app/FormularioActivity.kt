package com.example.metropolis_app

import android.R
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.example.metropolis_app.databinding.ActivityFormularioBinding
import com.example.metropolis_app.databinding.ActivityMainBinding
import java.util.Calendar

class FormularioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormularioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //spinner
        val spinner = binding.spinner
        val items = arrayOf("Sala A", "Sala B", "Sala C", "Padock", "Sala Conf. 1")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        //seekbar
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

        //datepicker
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        binding.tvFechainicio.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val date = "$dayOfMonth/${month + 1}/$year"
                binding.tvFechainicio.text = date
            }, year, month, day)
            datePickerDialog.show()
        }
        /*SPINNER
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        binding.tvFechainicio.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, { _, year, month, dayOfMonth ->
                val date = "$dayOfMonth/${month + 1}/$year"
                binding.tvFechainicio.text = date
            }, year, month, day)
            datePickerDialog.datePicker.calendarViewShown = false
            datePickerDialog.show()
        }
        * */
    }
}