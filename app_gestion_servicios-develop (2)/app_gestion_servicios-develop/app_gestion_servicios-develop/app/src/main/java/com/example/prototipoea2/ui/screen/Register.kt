package com.example.prototipoea2.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prototipoea2.ui.components.Footer
import com.example.prototipoea2.ui.components.Header
import com.example.prototipoea2.ui.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    Scaffold(
        topBar = { Header("REGISTRO") },
        bottomBar = { Footer() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Crear cuenta",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = state.rut,
                onValueChange = { viewModel.onRutChanged(it) },
                label = { Text("RUT") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                isError = state.hasTriedRegister && state.rut.isBlank(),
                trailingIcon = {
                    if (state.hasTriedRegister && state.rut.isBlank()) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "RUT obligatorio",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = state.telefono,
                onValueChange = { viewModel.onTelefonoChanged(it) },
                label = { Text("Teléfono") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                isError = state.hasTriedRegister && state.telefono.isBlank(),
                trailingIcon = {
                    if (state.hasTriedRegister && state.telefono.isBlank()) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "Teléfono obligatorio",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = state.nombre,
                onValueChange = { viewModel.onNombreChanged(it) },
                label = { Text("Nombre") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                isError = state.hasTriedRegister && state.nombre.isBlank(),
                trailingIcon = {
                    if (state.hasTriedRegister && state.nombre.isBlank()) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "Nombre obligatorio",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = state.apellidos,
                onValueChange = { viewModel.onApellidosChanged(it) },
                label = { Text("Apellidos") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                isError = state.hasTriedRegister && state.apellidos.isBlank(),
                trailingIcon = {
                    if (state.hasTriedRegister && state.apellidos.isBlank()) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "Apellidos obligatorios",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                label = { Text("Correo electrónico") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                isError = (state.hasTriedRegister && state.email.isBlank()) ||
                        state.emailError,
                trailingIcon = {
                    if ((state.hasTriedRegister && state.email.isBlank()) ||
                        state.emailError
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "Correo electrónico incorrecto",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            state.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    viewModel.onRegisterClick(context) {
                        Toast.makeText(
                            context,
                            "¡Registro exitoso! Por favor inicia sesión.",
                            Toast.LENGTH_SHORT
                        ).show()

                        onNavigateToLogin()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }
        }
    }
}