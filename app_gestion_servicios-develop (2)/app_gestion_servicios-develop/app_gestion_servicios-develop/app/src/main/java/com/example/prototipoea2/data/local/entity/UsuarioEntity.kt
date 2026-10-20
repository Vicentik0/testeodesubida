package com.example.prototipoea2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val rut: String,
    val telefono: String,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val password: String
)