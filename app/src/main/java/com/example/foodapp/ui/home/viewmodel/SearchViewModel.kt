package com.example.foodapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.model.MenuData
import com.example.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: FoodRepository
): ViewModel() {

    fun addMenu(menuData: List<MenuData>){
        viewModelScope.launch {
            repository.addMenuDetails(menuData)
        }
    }

    fun searchForMeal(name: String) : LiveData<List<MenuData>> {
        return repository.searchInMenu(name)
    }

}