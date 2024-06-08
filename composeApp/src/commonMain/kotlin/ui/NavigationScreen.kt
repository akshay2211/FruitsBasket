package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.screens.CartScreen
import ui.screens.DetailScreen
import ui.screens.HomeScreen

@Composable
fun NavigationScreen(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController,
        startDestination = Screens.HOME.name,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        composable(Screens.HOME.name) {
            HomeScreen { navController.navigate(it.name) }
        }
        composable(Screens.DETAIL.name) {
            DetailScreen(onBackPress = {
                navController.popBackStack()
            }) { navController.navigate(Screens.CART.name) }
        }
        composable(Screens.CART.name) {
            CartScreen(onBackPress = { navController.popBackStack() }) {
                navController.navigate(Screens.DETAIL.name)
            }
        }
    }
}