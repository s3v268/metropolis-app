package com.example.metropolis_app.home

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityHomeBinding
import com.example.metropolis_app.espacios.EspaciosActivity
import com.example.metropolis_app.formulario.FormularioActivity
import com.example.metropolis_app.perfil.PerfilActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewmodel
    /*Se puede acceder a la constante PREFS_EMAIL desde cualquier parte de la clase principal o incluso desde otras clases relacionadas simplemente utilizando MainActivity.PREFS_EMAIL.*/
    companion object {
        const val  PREFS_EMAIL = "email"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkAndCreateEmail()
        configureView()
    }

    private fun configureView() {
        binding.homeBtnReservas.setOnClickListener{
            startActivity(Intent(this, FormularioActivity ::class.java))

        }
        binding.homeBtnEspacios.setOnClickListener{
            startActivity(Intent(this, EspaciosActivity ::class.java))
        }
        binding.homeBtnPerfil.setOnClickListener{
            startActivity(Intent(this, PerfilActivity ::class.java))
        }
    }

    private fun checkAndCreateEmail() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString(PREFS_EMAIL, null)

        if (userEmail != null) {
            // El correo electrónico ya está guardado, puedes realizar las acciones necesarias
            // en función de esta condición, por ejemplo, iniciar sesión automáticamente.
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Ingresa tu correo electrónico")

            val input = EditText(this)
            input.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

            // Personaliza el color del campo de texto con el color primario de tu aplicación
            val primaryColor = ContextCompat.getColor(this, R.color.primary_color)
            input.backgroundTintList = ColorStateList.valueOf(primaryColor)

            builder.setView(input)

            builder.setPositiveButton("Guardar") { _, _ ->
                val enteredEmail = input.text.toString()

                if (enteredEmail.isEmpty()) {
                    // Vuelve a mostrar el diálogo si el campo de correo está vacío
                    checkAndCreateEmail()
                    return@setPositiveButton
                }

                // Guarda el correo electrónico en SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString(PREFS_EMAIL, enteredEmail)
                editor.apply()

                // Realiza las acciones necesarias con el correo electrónico ingresado.
            }

            /*builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }*/

            builder.show()
        }
    }

}