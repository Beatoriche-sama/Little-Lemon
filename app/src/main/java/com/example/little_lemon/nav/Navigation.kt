package com.example.little_lemon.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.little_lemon.MenuViewModel
import com.example.little_lemon.PrefManager
import com.example.little_lemon.ui.screens.cart.Cart
import com.example.little_lemon.ui.screens.Onboarding
import com.example.little_lemon.ui.screens.Profile
import com.example.little_lemon.ui.screens.cart.PurchaseItem
import com.example.little_lemon.ui.screens.home.Home


private val homePath = Destinations.Home.path
private val registrationPath = Destinations.Onboarding.path

@Composable
fun Navigation(
    navController: NavHostController,
    context: Context
) {
    val startDest: String = if (PrefManager(context).isDataRegistered())
        homePath else registrationPath

    val menuViewModel: MenuViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDest) {

        composable(homePath) {
            Home(navController, menuViewModel)
        }
        composable(Destinations.Profile.path) {
            Profile(context, navController)
        }
        composable(registrationPath) {
            Onboarding(context = context, navController)
        }
        composable(Destinations.PurchaseItem.path) {
            PurchaseItem(navController, menuViewModel)
        }
        composable(Destinations.Cart.path) {
            Cart(menuViewModel, navController)
        }
    }
}
