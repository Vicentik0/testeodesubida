package com.example.prototipoea2.model

data class TrabajoModel(
    val nombreTrabajo: String="",
    val descripcion: String="",
    val fotoTrabajo: String?=null,
    val errorNombre: Boolean= false,
    val errorDescripcion: Boolean= false

)