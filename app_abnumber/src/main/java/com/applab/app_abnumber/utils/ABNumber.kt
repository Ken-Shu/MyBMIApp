package com.applab.app_abnumber.utils

class ABNumber {
private var ans  = ""

    fun setAns() {
        val nums = "5842"
        ans = nums
    }
    fun getresult(guess : String) : IntArray{
        var a = 0
        var b = 0
        for(i in ans.indices){
            if(ans[i].toInt() == guess[i].toInt()){
                a++
            }
        }
        for (i in ans.indices){
            for ( k in guess.indices){
                if(ans[i].toInt() == guess[k].toInt()){
                    b++
                }
            }
        }
        val result = IntArray(2)
        result[0] = a
        result[1] = b-a
        return result
    }
}