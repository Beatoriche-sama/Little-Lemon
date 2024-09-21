package com.example.little_lemon.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MenuEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract val menuDao: MenuDao
}