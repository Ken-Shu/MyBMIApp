package com.ken_shu.app_homepractice.util

import java.util.*

class guessUtil {

    private var ans: Int = 0
    private var min = 0;
    private var max = 100;
    var r = Random().nextInt(100) + 1
    fun setnumber(): Int {

        return ans + r
    }
    fun getnumber () : Int{
        return setnumber()
    }

    fun getresult(guess: Int): String {
    val err = "請重新輸入(0~100)"
        var a = setnumber()
        if (guess > max || guess < min) {
        return err
        } else {
            if (guess > a) {
                max = guess
            }else {
                if (guess < a) {
                    min = guess
                }
            }
        }
        var result = "${min.toString()} ~ ${max.toString()}"
        return result
    }
}