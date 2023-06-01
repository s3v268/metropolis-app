package com.example.metropolis_app.perfil

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.metropolis_app.R
import com.example.metropolis_app.databinding.ActivityPerfilBinding
import com.example.metropolis_app.home.HomeActivity.Companion.PREFS_EMAIL

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPerfilBinding
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mostrar el correo actual del usuario
        email = getEmailFromSharedPreferences().toString()
        binding.tvEmail.text = email

        changeEmail()
    }

    private fun changeEmail() {
        binding.btnModificarEmail.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Modificar correo electrónico")

            val input = EditText(this)
            input.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            input.setText(email)

            // Personaliza el color del campo de texto con el color primario de tu aplicación
            val primaryColor = ContextCompat.getColor(this, R.color.primary_color)
            input.backgroundTintList = ContextCompat.getColorStateList(this, R.color.primary_color)

            builder.setView(input)

            builder.setPositiveButton("Guardar") { _, _ ->
                val newEmail = input.text.toString()
                if (newEmail.isNotEmpty()) {
                    saveEmailToSharedPreferences(newEmail)
                    binding.tvEmail.text = newEmail
                } else {
                    // El correo electrónico no se ingresó, puedes mostrar un mensaje de error o tomar
                    // alguna otra acción adicional según sea necesario.
                }
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }

            builder.show()
        }
    }

    private fun getEmailFromSharedPreferences(): String? {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString(PREFS_EMAIL, null)
    }

    private fun saveEmailToSharedPreferences(email: String) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(PREFS_EMAIL, email)
        editor.apply()
    }
}
