package com.example.prototipoea2.model

data class ProfileModel(
    val rut: String = "",
    val telefono: String = "",
    val nombre: String = "",
    val apellidos: String = "",
    val email: String = "",
    val trabajosRealizados: Int = 0,
    val trabajosEnProceso: Int = 0
)