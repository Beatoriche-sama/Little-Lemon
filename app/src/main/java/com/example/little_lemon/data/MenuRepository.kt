package com.example.little_lemon.data

import androidx.lifecycle.LiveData
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val menuDao: MenuDao
){
    private val menuItems: LiveData<List<MenuEntity?>?> = menuDao.getAllMenuItems()!!

    fun deleteAllMenuItems(){
        menuDao.deleteAll()
    }

    fun insertMenuItem(menuEntity: MenuEntity){
        menuDao.insert(menuEntity)
    }

    fun getAllMenuItems(): LiveData<List<MenuEntity?>?> {
        return menuItems
    }
}