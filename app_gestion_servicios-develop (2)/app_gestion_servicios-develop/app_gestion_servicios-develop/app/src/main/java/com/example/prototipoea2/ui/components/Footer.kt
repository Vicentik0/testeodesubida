package com.example.prototipoea2.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Footer(
    backGroundColor: Color = Color(0xFF0B0B0B)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(backGroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Gestión de Servicios",
            color = Color.White
        )
    }
}

