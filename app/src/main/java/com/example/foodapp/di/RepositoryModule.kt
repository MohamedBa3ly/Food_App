package com.example.foodapp.di

import com.example.foodapp.repository.FoodRepository
import com.example.foodapp.repository.FoodRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

        @Binds
        abstract fun bindMyRepository(moviesRepoImpl:FoodRepositoryImpl): FoodRepository

}