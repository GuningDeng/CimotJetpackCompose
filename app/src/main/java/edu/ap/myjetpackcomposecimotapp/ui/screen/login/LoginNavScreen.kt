package edu.ap.myjetpackcomposecimotapp.ui.screen.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ap.myjetpackcomposecimotapp.ui.route.LOGIN
import edu.ap.myjetpackcomposecimotapp.ui.route.LOGIN_OTHER
import edu.ap.myjetpackcomposecimotapp.ui.route.LOGIN_PHONE

@Composable
fun LoginNavScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LOGIN // default start page
    ) {
        composable(route = LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(route = LOGIN_OTHER) {
            LoginOtherScreen(navController = navController)
        }
        composable(
            route = "$LOGIN_PHONE/{phone}",
            arguments = listOf(navArgument("phone") {
                type = NavType.StringType
            })
        ) {
            val phone = it.arguments?.getString("phone") ?: ""
            LoginPhoneScreen(navController = navController, phone = phone)
        }
    }
}