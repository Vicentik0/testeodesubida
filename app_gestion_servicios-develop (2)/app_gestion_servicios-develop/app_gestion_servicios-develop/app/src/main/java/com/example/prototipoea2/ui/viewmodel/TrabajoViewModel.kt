package com.example.prototipoea2.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.prototipoea2.model.TrabajoModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class TrabajoViewModel: ViewModel() {
    var uiState by mutableStateOf(TrabajoModel())
        private set

    fun onNombreTrabajo(nombreTrabajo: String){
        uiState = uiState.copy(
            nombreTrabajo = nombreTrabajo,
            errorNombre = false
        )

    }
    fun onDescripcion(descripcion: String){
        uiState = uiState.copy(
            descripcion = descripcion,
            errorDescripcion = false
        )
    }
    fun onFotoTrabajo(fotoTrabajo: String?){
        uiState = uiState.copy(
            fotoTrabajo= fotoTrabajo
        )
    }
    fun onRegistrarTrabajoClick(){
        if (uiState.nombreTrabajo.isBlank() ||
            uiState.descripcion.isBlank()){
            uiState = uiState.copy(
                errorNombre = uiState.nombreTrabajo.isBlank(),
                errorDescripcion = uiState.descripcion.isBlank()
            )
        } else{
            uiState = uiState.copy(
                errorNombre = false,
                errorDescripcion = false
            )
        }

    }



}