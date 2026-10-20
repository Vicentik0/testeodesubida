package com.example.prototipoea2.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototipoea2.data.database.AppDatabase
import com.example.prototipoea2.data.local.entity.UsuarioEntity
import com.example.prototipoea2.model.RegisterModel
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var uiState by mutableStateOf(RegisterModel())
        private set
    fun onRutChanged(rut: String) {
        uiState = uiState.copy(
            rut = rut,
            errorMessage = null
        )
    }
    fun onTelefonoChanged(telefono: String) {
        uiState = uiState.copy(
            telefono = telefono,
            errorMessage = null
        )
    }
    fun onNombreChanged(nombre: String) {
        uiState = uiState.copy(
            nombre = nombre,
            errorMessage = null
        )
    }
    fun onApellidosChanged(apellidos: String) {
        uiState = uiState.copy(
            apellidos = apellidos,
            errorMessage = null
        )
    }
    fun onEmailChanged(email: String) {
        uiState = uiState.copy(
            email = email,
            errorMessage = null,
            emailError = false
        )
    }
    fun onRegisterClick(context: Context, onSuccess: () -> Unit) {
        val state = uiState

        uiState = uiState.copy(
            hasTriedRegister = true,
            emailError = false
        )
        if (
            state.rut.isBlank() ||
            state.telefono.isBlank() ||
            state.nombre.isBlank() ||
            state.apellidos.isBlank() ||
            state.email.isBlank()
        ) {
            uiState = uiState.copy(
                errorMessage = "Por favor completa todos los campos."
            )
            return
        }
        if (!state.email.trim().endsWith("@electromechanic.cl", ignoreCase = true)) {
            uiState = uiState.copy(
                errorMessage = "El correo debe pertenecer a @electromechanic.cl.",
                emailError = true
            )
            return
        }
        viewModelScope.launch {
            val db = AppDatabase.getDatabase(context)
            val existingUser = db.usuarioDao().getUserByEmail(state.email)

            if (existingUser != null) {
                uiState = uiState.copy(
                    errorMessage = "El correo ya está registrado.",
                    emailError = true
                )
            } else {
                val password = state.rut
                    .replace(".", "")
                    .replace("-", "")
                    .trim()
                    .plus(state.nombre.trim().first().uppercaseChar())

                val newUser = UsuarioEntity(
                    rut = state.rut,
                    telefono = state.telefono,
                    nombre = state.nombre,
                    apellidos = state.apellidos,
                    email = state.email,
                    password = password
                )
                db.usuarioDao().registerUser(newUser)
                uiState = uiState.copy(
                    errorMessage = null,
                    emailError = false
                )
                onSuccess()
            }
        }
    }
}