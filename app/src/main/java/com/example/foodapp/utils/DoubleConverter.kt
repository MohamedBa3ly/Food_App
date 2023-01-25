package com.example.foodapp.utils

import androidx.databinding.InverseMethod

object DoubleConverter {

    //Convert Double to String :
    @InverseMethod("doubleToString")
    @JvmStatic
    fun doubleToString(value: Double): String{
        return value.toString()
    }
}