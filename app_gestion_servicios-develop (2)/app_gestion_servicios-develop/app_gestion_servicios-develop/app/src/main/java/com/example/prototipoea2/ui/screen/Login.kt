package com.example.prototipoea2.ui.screen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prototipoea2.ui.components.Footer
import com.example.prototipoea2.ui.components.Header
import com.example.prototipoea2.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToProfile: (String) -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    Scaffold(
        topBar = { Header("INICIAR SESIÓN") },
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
                text = "Iniciar sesión",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                label = { Text("Correo electrónico") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                isError = (state.hasTriedLogin && state.email.isBlank()) ||
                        state.emailError,
                trailingIcon = {
                    if ((state.hasTriedLogin && state.email.isBlank()) ||
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

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                isError = (state.hasTriedLogin && state.password.isBlank()) ||
                        state.passwordError,
                trailingIcon = {
                    if ((state.hasTriedLogin && state.password.isBlank()) ||
                        state.passwordError
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "Contraseña incorrecta",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            if (viewModel.errorMessage != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = viewModel.errorMessage!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.onLoginClick(context) { user ->
                        onNavigateToProfile(user.email)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onNavigateToRegister,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear cuenta")
            }
        }
    }
}