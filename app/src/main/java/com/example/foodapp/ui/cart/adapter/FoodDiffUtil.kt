package com.example.foodapp.ui.cart.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.foodapp.room.entity.CartData

class FoodDiffUtil (
    private val oldCartList: List<CartData>,
    private val newCartList: List<CartData>
): DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldCartList.size
    }

    override fun getNewListSize(): Int {
        return newCartList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCartList[oldItemPosition].id == newCartList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldCartList[oldItemPosition].id != newCartList[newItemPosition].id ->{
                false
            }
            oldCartList[oldItemPosition].name != newCartList[newItemPosition].name ->{
                false
            }
            oldCartList[oldItemPosition].size != newCartList[newItemPosition].size ->{
                false
            }
            oldCartList[oldItemPosition].price != newCartList[newItemPosition].price ->{
                false
            }
            oldCartList[oldItemPosition].number != newCartList[newItemPosition].number ->{
                false
            }
            else -> true
        }
    }
}