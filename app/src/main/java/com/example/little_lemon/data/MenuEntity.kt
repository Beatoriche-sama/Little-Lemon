package com.example.little_lemon.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.little_lemon.network.Network

@Entity(tableName = "MENU")
data class MenuEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
) {
    companion object {
        fun menuItemNetworkToMenuEntity(
            menuItemNetwork: Network.MenuItemNetwork
        ): MenuEntity {
            return MenuEntity(
                id = menuItemNetwork.id,
                title = menuItemNetwork.title,
                description = menuItemNetwork.description,
                price = menuItemNetwork.price,
                image = menuItemNetwork.image,
                category = menuItemNetwork.category
            )

        }

    }
}