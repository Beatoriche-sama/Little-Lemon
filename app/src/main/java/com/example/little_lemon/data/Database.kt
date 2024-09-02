package com.example.little_lemon.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase


@androidx.room.Database(entities = [MenuEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    protected abstract val menuDao: MenuDao

    companion object {
        private var instance: Database? = null
        private const val DATABASE_NAME = "USER_DATA.DB"

        @Synchronized
        fun getInstance(context: Context): Database? {
            if (instance == null) {
                val builder = databaseBuilder(
                    context,
                    Database::class.java, DATABASE_NAME
                )

                if (context.getDatabasePath(DATABASE_NAME).exists()) {
                    builder.createFromAsset("databases/$DATABASE_NAME")
                }

                instance = builder.build()
            }
            return instance
        }
    }

    fun clearAllData(){
        menuDao.deleteAll()
    }

    fun insert(menuEntity: MenuEntity) {
        menuDao.insert(menuEntity)
    }

    fun getAllMenuItems(): LiveData<List<MenuEntity?>?>? {
        return menuDao.getAllMenuItems()
    }

}