package com.example.foodapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "menu_table")
data class MenuData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val image: Int,
    val image2 : Int,
    val Description: String,
    val name: String,
    val price: Double,
    val priceLarge: Double,
    val discount: String
):Parcelable


