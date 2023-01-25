package com.example.foodapp.ui.home.adapter
import androidx.recyclerview.widget.DiffUtil
import com.example.foodapp.model.MenuData


class SearchDiffUtil(
    private val oldCartList: List<MenuData>,
    private val newCartList: List<MenuData>
): DiffUtil.Callback(){

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
            oldCartList[oldItemPosition].image != newCartList[newItemPosition].image ->{
                false
            }
            oldCartList[oldItemPosition].image2 != newCartList[newItemPosition].image2 ->{
                false
            }

            oldCartList[oldItemPosition].Description != newCartList[newItemPosition].Description ->{
                false
            }

            oldCartList[oldItemPosition].price != newCartList[newItemPosition].price ->{
                false
            }

            oldCartList[oldItemPosition].discount != newCartList[newItemPosition].discount->{
                false
            }

            else -> true
        }
    }
}