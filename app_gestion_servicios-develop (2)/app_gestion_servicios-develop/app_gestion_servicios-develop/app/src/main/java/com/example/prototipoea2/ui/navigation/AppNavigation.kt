package com.example.prototipoea2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prototipoea2.ui.screen.LoginScreen
import com.example.prototipoea2.ui.screen.ProfileScreen
import com.example.prototipoea2.ui.screen.RegisterScreen
object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val PROFILE = "profile/{email}"
}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER)
                },
                onNavigateToProfile = { email ->
                    navController.navigate("profile/$email")
                }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Routes.PROFILE) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email").orEmpty()

            ProfileScreen(
                email = email,
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}