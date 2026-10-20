package com.example.prototipoea2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.prototipoea2.ui.navigation.AppNavigation
import com.example.prototipoea2.ui.theme.PrototipoEA2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PrototipoEA2Theme {
                AppNavigation()
            }
        }
    }
}