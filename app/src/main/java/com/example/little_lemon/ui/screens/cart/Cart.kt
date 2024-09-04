package com.example.little_lemon.ui.screens.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.little_lemon.MenuViewModel
import com.example.little_lemon.nav.Destinations
import com.example.little_lemon.ui.screens.home.MealCard

@Composable
fun Cart(
    menuViewModel: MenuViewModel,
    navController: NavHostController
) {
    val items = menuViewModel.cartItems

    Column {
        Spacer(modifier = Modifier.padding(0.dp, 20.dp))

        LazyColumn {
            items(items.toList()) {
                Column {
                    MealCard(menuEntity = it)
                    Button(onClick = {
                        items.remove(it)
                    }) {
                        Text("Remove")
                    }
                }
            }
        }

        Button(
            onClick = {
                navController.navigate(Destinations.Home.path)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Continue shopping")
        }
    }

}

