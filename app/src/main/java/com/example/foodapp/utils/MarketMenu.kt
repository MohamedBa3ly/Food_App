package com.example.foodapp.utils

import com.example.foodapp.R
import com.example.foodapp.model.MenuData

object MarketMenu{

    fun getMarketMenu(): List<MenuData>{
        return listOf(
            //Burger:
            MenuData(1, R.drawable.chicken_burger,R.drawable.chicken_burger,"Burger","Chicken Burger",85.0,90.0,"15%"),
            MenuData(2,R.drawable.cheeseburger,R.drawable.cheeseburger,"Cheese burger","Cheese Burger",70.0,80.0,"30%"),
            MenuData(3,R.drawable.blackburger,R.drawable.blackburger,"Black Burger","Black Burger",60.0,100.0,"20%"),
            MenuData(4,R.drawable.mexicanburger,R.drawable.mexicanburger,"Mexican Burger","Mexican Burger",40.0,100.0,"20%"),
            MenuData(5,R.drawable.chickenspicyburger,R.drawable.chickenspicyburger,"Chicken Spicy Burger","Chicken Spicy Burger",75.0,100.0,"20%"),
            //Pizza:
            MenuData(6,R.drawable.pizzaapiprony,R.drawable.pizzaapiprony,"Our Pepperoni Pizza features our crispy thin crust topped with real cheese and scrumptious pepperoni for a classic taste that will never fade away.","Pizza Piprony",60.0,100.0,"50%"),
            MenuData(7,R.drawable.pzzasalami,R.drawable.pzzasalami,"Pizza salami","Pizza Salami",65.0,100.0,"30%"),
            MenuData(8,R.drawable.mshrompizza,R.drawable.mshrompizza,"Mushroom Pizza","Mushroom Pizza",65.0,100.0,"30%"),
            MenuData(9,R.drawable.meatpizza,R.drawable.meatpizza,"Meat Pizza","Meat Pizza",70.0,100.0,"30%"),
            MenuData(10,R.drawable.margritapizza,R.drawable.margritapizza,"Margarita Pizza","Margarita Pizza",65.0,100.0,"30%"),
            //Fried Chicken :
            MenuData(11,R.drawable.friedchicken2,R.drawable.friedchicken2,"Fried Chicken","Fried Chicken",78.0,100.0,"50%"),
            MenuData(12,R.drawable.chicken_fajita,R.drawable.chicken_fajita,"Fajita","Chicken Fajita",65.0,100.0,"30%"),
            //Salad :
            MenuData(13,R.drawable.fresh_salad,R.drawable.fresh_salad,"salad","Fresh Salad",55.0,100.0,"50%"),
            MenuData(14,R.drawable.chicken_salad,R.drawable.chicken_salad,"Chicken Salad","Chicken Salad",34.0,100.0,"30%"),
            //Drinks:
            MenuData(15,R.drawable.juice,R.drawable.juice,"Lemon","Lemon",30.0,100.0,"50%"),
            MenuData(16,R.drawable.drink,R.drawable.drink,"Orange","Orange",25.0,100.0,"30%")
        )
    }


}



