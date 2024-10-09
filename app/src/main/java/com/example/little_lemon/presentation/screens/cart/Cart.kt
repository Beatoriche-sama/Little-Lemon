package com.example.little_lemon.presentation.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun Cart(
    viewModel: MenuViewModel,
    onNavigateToHome: () -> Unit
) {
    val items = viewModel.cartItems

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.padding(0.dp, 20.dp))

        LazyColumn {
            items(items.toList()) {
                Column {
                    MealCard(menuEntity = it)
                    Button(
                        onClick = {
                            items.remove(it)
                        },
                        shape = RectangleShape
                    ) {
                        Text("Remove")
                    }
                }
            }
        }

        Button(
            onClick = { onNavigateToHome() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            shape = RectangleShape
        ) {
            Text(text = "Continue shopping")
        }
    }

}

