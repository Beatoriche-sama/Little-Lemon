package com.example.little_lemon.data



import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface MenuDao {
    @Insert
    fun insert(menuEntity: MenuEntity)

    @Delete
    fun delete(menuEntity: MenuEntity)

    @Update
    fun update(menuEntity: MenuEntity)

    @Query("DELETE FROM MENU")
    fun deleteAll()

    @Query("SELECT * FROM MENU")
    fun getAllMenuItems(): LiveData<List<MenuEntity?>?>?
}