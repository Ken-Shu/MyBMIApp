package com.applab.app_luckynumber.utils

import java.util.*

class LuckyNumber {
    //幸運數字答案
    val ans : Int = Random().nextInt(9) + 1
    //猜的次數
    var count = 1
    //檢查所猜的數字 - 幸運數字之間的差
    fun validate(guess : Int) : Int{
        count++
        //0 : 答對了 ,<0 猜太小 ,>0 猜太大
        return guess - ans
    }
}