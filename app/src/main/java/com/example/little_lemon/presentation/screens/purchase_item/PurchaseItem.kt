package com.example.little_lemon.presentation.screens.purchase_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.little_lemon.presentation.MenuViewModel
import com.example.little_lemon.presentation.common.MealCard


@Composable
fun PurchaseItem(
    viewModel: MenuViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToCart: () -> Unit
) {

    val meal = viewModel.clickedMeal

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        meal?.let { MealCard(it) }

        Spacer(modifier = Modifier.padding(20.dp, 10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                meal?.let { viewModel.cartItems.add(it) }
            },
                shape = RectangleShape
            ) {
                Text(text = "Add to cart")
            }

            Button(
                onClick = { onNavigateToCart() },
                shape = RectangleShape
            ) {
                Text(text = "Go to Cart")
            }

            Button(
                onClick = { onNavigateToHome() },
                shape = RectangleShape
            ) {
                Text(text = "Go to Home")
            }
        }

    }


}