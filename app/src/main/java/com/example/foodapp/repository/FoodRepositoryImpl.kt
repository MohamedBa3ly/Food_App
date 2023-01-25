package com.example.foodapp.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import com.example.foodapp.model.MenuData
import com.example.foodapp.model.ProfileData
import com.example.foodapp.room.FoodDao
import com.example.foodapp.room.entity.CartData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//Data store will use to store profile data for delivery :
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Delivery")

class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao,
) : FoodRepository {

    //Fun for Room Data Base: read,add and delete :
    override fun readAllCartDetails(): LiveData<List<CartData>> {
        return foodDao.readAllCartDetails()
    }

    override suspend fun addCartDetails(cartDetails: CartData) {
        return foodDao.addCartDetails(cartDetails)
    }

    override suspend fun updateLastOrders(cartDetails: CartData) {
        return foodDao.updateLastOrders(cartDetails)
    }

    override suspend fun deleteCartDetails(cartDetails: CartData) {
        return foodDao.deleteCartDetails(cartDetails)
    }

    override suspend fun deleteAllCartDetails() {
        return foodDao.deleteAllCartDetails()
    }

    //Fun to save profile data by Data Store :
    override suspend fun saveData(context:Context,profileData: ProfileData) {
        context.dataStore.edit {
             it[stringPreferencesKey("name")] = profileData.name ?:"Name"
            it[stringPreferencesKey("address")] = profileData.address ?:"Address"
            it[stringPreferencesKey("phone")] = profileData.phone ?: "Phone"
        }
    }

    //Fun to read profile data by Data Store :
    override suspend fun readData(context:Context) = context.dataStore.data.map {
        ProfileData(
            name = it[stringPreferencesKey("name")],
            address = it[stringPreferencesKey("address")],
            phone = it[stringPreferencesKey("phone")]
        )
    }

    //Insert and search menu data :
    override suspend fun addMenuDetails(menuData: List<MenuData>) {
        return foodDao.addMenuDetails(menuData)
    }

    override fun searchInMenu(foodName: String): LiveData<List<MenuData>> {
        return foodDao.getFoodByName(foodName)
    }


}