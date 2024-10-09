package com.example.little_lemon.presentation.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.little_lemon.R
import com.example.little_lemon.data.MenuEntity
import com.example.little_lemon.presentation.MenuViewModel
import com.example.little_lemon.presentation.common.CardContents
import com.example.little_lemon.presentation.common.CategoryButton
import com.example.little_lemon.presentation.common.LemonLogo
import com.example.little_lemon.presentation.common.MealCard
import com.example.little_lemon.presentation.common.SearchBar
import com.example.little_lemon.presentation.theme.Corn_Silk
import com.example.little_lemon.presentation.theme.Custom_Green
import com.example.little_lemon.presentation.theme.Custom_Light_Green
import com.example.little_lemon.presentation.theme.Custom_Pink


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    viewModel: MenuViewModel,
    onNavigateToProfile: () -> Unit,
    onNavigateToPurchaseItem: () -> Unit,
    onNavigateToCart: () -> Unit
) {

    Scaffold(
        topBar = {},
        bottomBar = { CartButton(onClick = onNavigateToCart) },
        content = {
            HomeContent(
                viewModel = viewModel,
                onNavigateToProfile = onNavigateToProfile,
                onNavigateToPurchaseItem = onNavigateToPurchaseItem
            )
        }
    )

}

@Composable
private fun HomeContent(
    viewModel: MenuViewModel,
    onNavigateToProfile: () -> Unit,
    onNavigateToPurchaseItem: () -> Unit
) {
    val menuStateValue = viewModel.menuItems.observeAsState().value
    var menuList by remember(key1 = menuStateValue) {
        mutableStateOf(menuStateValue)
    }

    Column {
        Header(onNavigateToProfile = onNavigateToProfile)

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Custom_Light_Green
            )
        ) {
            CardContents()
            SearchBar(onSearch = {
                menuList =
                    if (it.isEmpty())
                        menuStateValue
                    else
                        filterMenuItems(it, { m -> m?.title }, menuStateValue)
            })
        }

        Spacer(modifier = Modifier.height(20.dp))

        val categories = arrayOf("starters", "desserts", "mains", "drinks")
        Row {
            categories.forEach { category ->
                CategoryButton(categoryName = category, onSearch = {
                    menuList = filterMenuItems(category, { m -> m?.category }, menuStateValue)
                })
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            if (menuList != null)
                items(menuList!!.toList()) {
                    if (it != null)
                        MenuItem(
                            menuEntity = it,
                            onMenuItemClick = {
                                viewModel.clickedMeal = it
                                onNavigateToPurchaseItem()
                            }
                        )
                }
        }

    }
}

private fun filterMenuItems(
    searchPhrase: String,
    attribute: (MenuEntity?) -> String?,
    menuItems: List<MenuEntity?>?
): List<MenuEntity?>? {
    return menuItems?.filter {
        attribute(it)?.contains(searchPhrase, ignoreCase = true) ?: true
    }
}

@Composable
private fun MenuItem(
    menuEntity: MenuEntity,
    onMenuItemClick: () -> Unit,
) {

    Card(
        shape = RectangleShape,
        border = BorderStroke(2.dp, Custom_Green),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Corn_Silk,
            contentColor = Color.Black
        ),
        onClick = { onMenuItemClick() }
    ) {
        MealCard(menuEntity = menuEntity)
    }
}

@Composable
private fun Header(onNavigateToProfile: () -> Unit) {
    Row(
        modifier = Modifier.padding(0.dp, 20.dp)
    ) {
        LemonLogo(
            modifier = Modifier
                .weight(1f, true)
                .height(90.dp)
                .padding(top = 20.dp, bottom = 10.dp)
        )

        Button(
            onClick = { onNavigateToProfile() },
            content = {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )
            },
            elevation = null,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        )
    }
}

@Composable
private fun CartButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        content = {
            Image(
                painter = painterResource(id = R.drawable.cart),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )
        },
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            containerColor = Custom_Pink,
            contentColor = Color.Black
        ),
        shape = CircleShape
    )
}







