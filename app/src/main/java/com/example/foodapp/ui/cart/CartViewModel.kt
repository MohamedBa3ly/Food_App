package com.example.foodapp.ui.cart


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.repository.FoodRepository
import com.example.foodapp.room.entity.CartData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: FoodRepository
): ViewModel() {

    //Fun to read all data :
    fun readAllCart() : LiveData<List<CartData>>{
        return repository.readAllCartDetails()
    }

    //Fun to add data in database :
    fun addCart(cart: CartData){
        viewModelScope.launch {
            repository.addCartDetails(cart)
        }
    }

    //Fun to add data in database :
    fun updateCart(cart: CartData){
        viewModelScope.launch {
            repository.updateLastOrders(cart)
        }
    }

    //Fun to delete only one cart data in database :
    fun deleteCart(cart: CartData){
        viewModelScope.launch {
            repository.deleteCartDetails(cart)
        }
    }

    //Fun to delete all cart data in database :
    fun deleteAllCart(){
        viewModelScope.launch {
            repository.deleteAllCartDetails()
        }
    }

}