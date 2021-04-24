package com.applab.app_food

//定義json 的 data class
data class Food(
    val idName: String,
    val name : String,
    val price:Int,
    val spicy:Boolean,
    val new : Boolean
)