package com.example.little_lemon.ui.screens.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.little_lemon.MenuViewModel
import com.example.little_lemon.nav.Destinations
import com.example.little_lemon.ui.screens.home.MealCard

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PurchaseItem(navController: NavController, menuViewModel: MenuViewModel) {

    val meal = menuViewModel.clickedMeal

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        meal?.let { MealCard(it) }

        Spacer(modifier = Modifier.padding(20.dp, 10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Button(onClick = {
                meal?.let { menuViewModel.cartItems.add(it) }
            }) {
                Text(text = "Add to cart")
            }

            Button(onClick = {
                navController.navigate(Destinations.Cart.path)
            }) {
                Text(text = "Go to Cart")
            }

            Button(onClick = {
                navController.navigate(Destinations.Home.path)
            }) {
                Text(text = "Go to Home")
            }
        }

    }


}