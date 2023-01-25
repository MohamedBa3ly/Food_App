package com.example.foodapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.foodapp.model.MenuData
import com.example.foodapp.room.entity.CartData

@Dao
interface FoodDao {

    //To insert cart data in food data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartDetails(cartDetails:CartData)

    //To read all cart data in food data base :
    @Query("SELECT * FROM food_table")
    fun readAllCartDetails() : LiveData<List<CartData>>

    //To Update all cart data in food data base :
    @Update
    suspend fun updateLastOrders(cartDetails: CartData)

    //To delete cart data in food data base :
    @Delete
    suspend fun deleteCartDetails(cartDetails: CartData)

    //To delete all cart data in food data base :
    @Query("DELETE FROM food_table")
    suspend fun deleteAllCartDetails()


    //To insert menu data in food data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMenuDetails(menuDetails:List<MenuData>)

    //To search by food name :
    @Query("SELECT * FROM menu_table WHERE name LIKE '%' || :name || '%' COLLATE NOCASE" )
    fun getFoodByName(name: String): LiveData<List<MenuData>>





}