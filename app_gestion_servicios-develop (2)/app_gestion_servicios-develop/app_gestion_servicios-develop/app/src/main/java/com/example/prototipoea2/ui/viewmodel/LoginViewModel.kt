package com.example.prototipoea2.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototipoea2.data.database.AppDatabase
import com.example.prototipoea2.data.local.entity.UsuarioEntity
import com.example.prototipoea2.model.LoginModel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var uiState by mutableStateOf(LoginModel())
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set
    fun onEmailChanged(email: String) {
        uiState = uiState.copy(
            email = email,
            emailError = false
        )
        errorMessage = null
    }
    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(
            password = password,
            passwordError = false
        )
        errorMessage = null
    }
    fun onLoginClick(context: Context, onSuccess: (UsuarioEntity) -> Unit) {
        val email = uiState.email
        val password = uiState.password

        uiState = uiState.copy(
            hasTriedLogin = true,
            emailError = false,
            passwordError = false
        )
        if (email.isBlank() || password.isBlank()) {
            uiState = uiState.copy(
                emailError = email.isBlank(),
                passwordError = password.isBlank()
            )
            errorMessage = "Ingresa correo y contraseña."
            return
        }
        if (!email.trim().endsWith("@electromechanic.cl", ignoreCase = true)) {
            uiState = uiState.copy(emailError = true)
            errorMessage = "Debes ingresar un correo @electromechanic.cl."
            return
        }
        viewModelScope.launch {
            val db = AppDatabase.getDatabase(context)
            val user = db.usuarioDao().getUserByEmail(email)

            if (user != null && user.password == password) {
                errorMessage = null
                onSuccess(user)
            } else {
                uiState = uiState.copy(
                    emailError = true,
                    passwordError = true
                )
                errorMessage = "Correo o contraseña incorrectos."
            }
        }
    }
}