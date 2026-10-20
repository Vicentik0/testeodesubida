package com.example.prototipoea2.model

data class LoginModel(
    val email: String = "",
    val password: String = "",
    val hasTriedLogin: Boolean = false,
    val emailError: Boolean = false,
    val passwordError: Boolean = false
)