package com.example.foodapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodapp.model.MenuData
import com.example.foodapp.room.entity.CartData

@Database(entities = [CartData::class, MenuData::class], version = 1, exportSchema = false)
abstract class FoodDataBase : RoomDatabase() {

    abstract fun foodDao():FoodDao

    companion object{
        @Volatile
        private var Instance : FoodDataBase?=null
        fun getInstance(context: Context):FoodDataBase{
            val tempInstance = Instance
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    FoodDataBase::class.java,
                    "Food_DataBase").build()
                Instance = instance
                return instance
            }
        }
    }
}