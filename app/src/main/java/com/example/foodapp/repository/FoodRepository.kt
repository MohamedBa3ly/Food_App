package com.example.foodapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.foodapp.model.MenuData
import com.example.foodapp.model.ProfileData
import com.example.foodapp.room.entity.CartData
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    //Fun for Room Data Base: read,add and delete :)
    fun readAllCartDetails(): LiveData<List<CartData>>
    suspend fun addCartDetails(cartDetails: CartData)
    suspend fun updateLastOrders(cartDetails: CartData)
    suspend fun deleteCartDetails(cartDetails: CartData)
    suspend fun deleteAllCartDetails()

    //Data Store for save and read :
    suspend fun saveData(context:Context,profileData: ProfileData)
    suspend fun readData(context:Context): Flow<ProfileData>

    //Insert and search menu data :
    suspend fun addMenuDetails(menuData: List<MenuData>)
    fun searchInMenu(foodName: String): LiveData<List<MenuData>>


}