package com.example.little_lemon.presentation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.PrefManager
import com.example.little_lemon.presentation.MenuViewModel
import com.example.little_lemon.presentation.screens.cart.Cart
import com.example.little_lemon.presentation.screens.home.Home
import com.example.little_lemon.presentation.screens.login.Onboarding
import com.example.little_lemon.presentation.screens.profile.Profile
import com.example.little_lemon.presentation.screens.purchase_item.PurchaseItem
import kotlinx.serialization.Serializable


@Composable
fun Navigation() {

    val navController = rememberNavController()
    val context = LocalContext.current

    val startDest = if (PrefManager(context).isDataRegistered())
        Destinations.Home else Destinations.Onboarding

    val menuViewModel: MenuViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = startDest) {

        composable<Destinations.Home> {
            Home(
                viewModel = menuViewModel,
                onNavigateToProfile = { navController.navigate(Destinations.Profile) },
                onNavigateToPurchaseItem = { navController.navigate(Destinations.PurchaseItem) },
                onNavigateToCart = { navController.navigate(Destinations.Cart) }
            )
        }
        composable<Destinations.Profile> {
            Profile(context = context,
                onNavigateToOnboarding = { navController.navigate(Destinations.Onboarding) })
        }
        composable<Destinations.Onboarding> {
            Onboarding(
                context = context,
                onNavigateHome = { navController.navigate(Destinations.Home) })
        }
        composable<Destinations.PurchaseItem> {
            PurchaseItem(
                viewModel = menuViewModel,
                onNavigateToHome = { navController.navigate(Destinations.Home) },
                onNavigateToCart = { navController.navigate(Destinations.Cart) }
            )
        }
        composable<Destinations.Cart> {
            Cart(
                viewModel = menuViewModel,
                onNavigateToHome = { navController.navigate(Destinations.Home) }
            )
        }
    }
}

@Serializable
sealed class Destinations {

    @Serializable
    data object Onboarding : Destinations()

    @Serializable
    data object Home : Destinations()

    @Serializable
    data object Profile : Destinations()

    @Serializable
    data object PurchaseItem : Destinations()

    @Serializable
    data object Cart : Destinations()

}
