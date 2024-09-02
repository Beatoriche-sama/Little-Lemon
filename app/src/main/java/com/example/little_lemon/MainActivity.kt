package com.example.little_lemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.data.Database
import com.example.little_lemon.data.MenuEntity
import com.example.little_lemon.nav.Navigation
import com.example.little_lemon.network.Network
import com.example.little_lemon.ui.theme.LittleLemonTheme
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database: Database? = Database.getInstance(applicationContext)

        //testing without delay
        lifecycleScope.launch(Dispatchers.IO) {
            database?.clearAllData()
            addMenuData(database!!)
        }

        setContent {
            LittleLemonTheme {
                Surface {
                    Navigation(
                        navController = rememberNavController(),
                        context = applicationContext,
                        menuLiveData = database?.getAllMenuItems()?.observeAsState()
                    )
                }
            }
        }

        //testing with delay
        /* lifecycleScope.launch(Dispatchers.IO) {
             database?.clearAllData()
             delay(10000L) //10 sec
             println("UPDATING DATABASE")
             addMenuData(database!!)
         }*/

    }

    private suspend fun addMenuData(database: Database) {
        val menuNetworkItems = fetchMenuItems()
        menuNetworkItems.forEach { menuNetworkItem ->
            database.insert(
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