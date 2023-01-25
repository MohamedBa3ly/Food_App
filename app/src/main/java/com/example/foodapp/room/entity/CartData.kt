package com.example.foodapp.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "food_table")
data class CartData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val size: String,
    val price: Double?,
    val number: Int
):Parcelable
