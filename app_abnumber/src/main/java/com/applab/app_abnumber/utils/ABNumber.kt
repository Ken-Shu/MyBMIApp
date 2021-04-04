package com.applab.app_abnumber.utils

import java.util.*

class ABNumber {

    private var ans: String = ""

    fun setAns() {
        var r: Random = Random()
        var nums: LinkedHashSet<String> = LinkedHashSet()
        var num_size = 4
        var count = 0
        while (nums.size < num_size) {
            var r_num = r.nextInt(9)
            var n = r_num
            nums.add(n.toString())
            count++
        }
        ans += nums
    }

    fun getans(): String {
        val an = ans
        return an
    }

    fun getresult(guess: String): IntArray {
        var a = 0
        var b = 0
        var guess_count = 0
        var ans_number=1
        while (ans_number < 11){
            if(ans[ans_number].toInt() == guess[guess_count].toInt()){
                a++
                ans_number += 3
                guess_count ++
            }else{ans_number +=3
                guess_count++}
        }
//        if (ans[1].toInt() == guess[0].toInt()) {
//            a++
//        }
//        if ( ans[4].toInt() == guess[1].toInt()){
//            a++
//        }
//        if (ans[7].toInt() == guess[2].toInt() ){
//            a++
//        }
//        if ( ans[10].toInt() == guess[3].toInt()){
//            a++
//        }
        for (i in 1..10 step 3) {
            for (k in guess.indices) {
                if (ans[i].toInt() == guess[k].toInt()) {
                    b++
                }
            }
        }
        val result = IntArray(2)
        result[0] = a
        result[1] = b - a
        return result
    }
}