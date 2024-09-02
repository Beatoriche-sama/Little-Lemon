package com.example.little_lemon.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class Network {
    @Serializable
    data class MenuItemNetwork(
        @SerialName("id") val id: String,
        @SerialName("title") val title: String,
        @SerialName("description") val description: String,
        @SerialName("price") val price: String,
        @SerialName("image") val image: String,
        @SerialName("category") val category: String
    )

    @Serializable
    data class MenuNetworkData(@SerialName("menu") val menu: List<MenuItemNetwork>)
}