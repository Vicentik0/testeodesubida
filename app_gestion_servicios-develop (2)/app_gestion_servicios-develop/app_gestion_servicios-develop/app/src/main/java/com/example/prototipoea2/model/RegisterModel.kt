package com.example.prototipoea2.model

data class RegisterModel(
    val rut: String = "",
    val telefono: String = "",
    val nombre: String = "",
    val apellidos: String = "",
    val email: String = "",
    val errorMessage: String? = null,
    val hasTriedRegister: Boolean = false,
    val emailError: Boolean = false
)