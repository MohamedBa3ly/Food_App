package com.example.foodapp.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.foodapp.repository.FoodRepository
import com.example.foodapp.repository.FoodRepositoryImpl
import com.example.foodapp.room.FoodDao
import com.example.foodapp.room.FoodDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodModule {

    @Provides
    @Singleton
    fun getInstanceDB(context: Application): FoodDataBase {
        return FoodDataBase.getInstance(context)
    }

    @Provides
    @Singleton
    fun getMoviesDao(appDao: FoodDataBase): FoodDao {
        return appDao.foodDao()
    }


}