package com.example.little_lemon.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.little_lemon.data.MenuEntity
import com.example.little_lemon.data.MenuRepository
import com.example.little_lemon.network.Network
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType.Text.Html
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {
    var menuItems: LiveData<List<MenuEntity?>?> = menuRepository.getAllMenuItems()
    var clickedMeal: MenuEntity? = null
    var cartItems = mutableStateListOf<MenuEntity>()

    private val menuDataUrl = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/" +
            "Working-With-Data-API/main/menu.json"

    init {
        viewModelScope.launch(Dispatchers.IO) {
            menuRepository.deleteAllMenuItems()
            val menuNetworkItems = fetchMenuItems()
            menuNetworkItems.forEach { menuNetworkItem ->
                menuRepository.insertMenuItem(
                    MenuEntity.menuItemNetworkToMenuEntity(menuNetworkItem)
                )
            }
        }
    }

    private suspend fun fetchMenuItems(): List<Network.MenuItemNetwork> {
        val client = HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    register(Html, KotlinxSerializationConverter(Json))
                })
            }
        }

        val response: HttpResponse = client.get(menuDataUrl)
        return decodeMenuItems(response)
    }

    private suspend fun decodeMenuItems(response: HttpResponse)
            : List<Network.MenuItemNetwork> {
        val json = Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
        return json.decodeFromString<Network.MenuNetworkData>(
            response.bodyAsText()
        ).menu
    }

}