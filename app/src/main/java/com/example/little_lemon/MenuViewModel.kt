package com.example.little_lemon

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.little_lemon.data.Database
import com.example.little_lemon.data.MenuEntity
import com.example.little_lemon.network.Network
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

class MenuViewModel(context: Context) : ViewModel() {

    private val database: Database? = Database.getInstance(context)
    var menuItems: LiveData<List<MenuEntity?>?> = database?.getAllMenuItems()!!
    var clickedMeal : MenuEntity? = null
    var cartItems = mutableStateListOf<MenuEntity>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            populateMenuData()
        }
    }

    private suspend fun populateMenuData() {
        database?.clearAllData()

        val menuNetworkItems = fetchMenuItems()
        menuNetworkItems.forEach { menuNetworkItem ->
            database?.insert(
                MenuEntity.menuItemNetworkToMenuEntity(menuNetworkItem)
            )
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

        val url = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/" +
                "Working-With-Data-API/main/menu.json"
        val response: HttpResponse = client.get(url)
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