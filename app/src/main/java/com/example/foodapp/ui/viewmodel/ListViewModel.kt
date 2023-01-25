package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.model.MenuData

class ListViewModel: ViewModel() {

    private var _menuData = MutableLiveData<List<MenuData>>()

    val menuData: LiveData<List<MenuData>> = _menuData

    init {
        _menuData.value = mutableListOf()
    }


    //Fun to add data that will selected by user in home screen and navigate to menu screen :
    fun addList(listMenu: MenuData){
        _menuData.value = _menuData.value?.toMutableList()?.apply {
            add(listMenu)
        }
    }

    //Fun to remove data :
    fun removeData(){
        _menuData.value = _menuData.value?.toMutableList()?.apply {
            clear()
        }
    }


}