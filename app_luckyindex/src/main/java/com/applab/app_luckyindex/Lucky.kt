package com.applab.app_luckyindex

import kotlin.random.Random
import kotlin.math.abs

fun getLuckyIndex( n:Int) : Int{
    val r = Random.nextInt(9)+1
    //abs 絕對值
    return 100- (abs(n-r)*10)
}