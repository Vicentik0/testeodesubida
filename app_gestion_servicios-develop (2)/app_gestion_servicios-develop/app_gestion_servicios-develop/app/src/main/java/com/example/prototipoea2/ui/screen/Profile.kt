package com.example.prototipoea2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prototipoea2.ui.components.Footer
import com.example.prototipoea2.ui.components.Header
import com.example.prototipoea2.ui.viewmodel.ProfileViewModel
import androidx.compose.ui.platform.LocalContext
@Composable
fun ProfileScreen(
    email: String,
    onNavigateToLogin: () -> Unit,
    viewModel: ProfileViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(email) {
        viewModel.loadProfile(context, email)
    }

    Scaffold(
        topBar = {
            Header("MI PERFIL")
        },
        bottomBar = {
            Footer()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Datos personales",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileData("RUT", state.rut)
            ProfileData("Teléfono", state.telefono)
            ProfileData("Nombre", state.nombre)
            ProfileData("Apellidos", state.apellidos)
            ProfileData("Correo electrónico", state.email)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Resumen de trabajos",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Realizados",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = state.trabajosRealizados.toString(),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "En proceso",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = state.trabajosEnProceso.toString(),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onNavigateToLogin,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al inicio de sesión")
            }
        }
    }
}
@Composable
private fun ProfileData(
    label: String,
    value: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value.ifBlank { "No disponible" },
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}