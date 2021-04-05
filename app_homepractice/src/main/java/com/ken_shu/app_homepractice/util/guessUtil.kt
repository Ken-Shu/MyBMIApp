package com.ken_shu.app_homepractice.util

import java.util.*

class guessUtil {

    private var ans: Int = 0

    fun setnumber(): Int {
        var r = Random().nextInt(100) + 1
        return ans + r
    }

    fun getresult(guess: Int): String {
        var min = 0;
        var max = 100;
        var a = setnumber()
        if (guess > max || guess < min) {
            print("請重新輸入(0~100)")
        } else {
            if (guess > a) {
                max = guess
            }
            if (guess < a) {
                min = guess
            }
        }
        var result = "${min.toString()} ~ ${max.toString()}}"
        return result
    }
}