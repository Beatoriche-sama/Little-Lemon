package com.example.little_lemon

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.little_lemon.data.MenuDao
import com.example.little_lemon.data.MenuEntity
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
    private val menuDao: MenuDao
) : ViewModel() {
    var menuItems: LiveData<List<MenuEntity?>?> = menuDao.getAllMenuItems()!!
    var clickedMeal: MenuEntity? = null
    var cartItems = mutableStateListOf<MenuEntity>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            menuDao.deleteAll()
            val menuNetworkItems = fetchMenuItems()
            menuNetworkItems.forEach { menuNetworkItem ->
                menuDao.insert(
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