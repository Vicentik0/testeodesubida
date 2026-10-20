package com.example.prototipoea2.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototipoea2.data.database.AppDatabase
import com.example.prototipoea2.model.ProfileModel
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    var uiState by mutableStateOf(ProfileModel())
        private set

    fun loadProfile(context: Context, email: String) {
        viewModelScope.launch {
            val db = AppDatabase.getDatabase(context)
            val user = db.usuarioDao().getUserByEmail(email)

            if (user != null) {
                uiState = uiState.copy(
                    rut = user.rut,
                    telefono = user.telefono,
                    nombre = user.nombre,
                    apellidos = user.apellidos,
                    email = user.email
                )
            }
        }
    }
}