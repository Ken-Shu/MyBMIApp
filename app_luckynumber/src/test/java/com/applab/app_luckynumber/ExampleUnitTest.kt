package com.applab.app_luckynumber

import com.applab.app_luckynumber.utils.LuckyNumber
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test_luckyNumber(){
        val luckyNumber = LuckyNumber()
        val ans  =luckyNumber.ans
        val guess = 7
        val result = luckyNumber.validate(guess)
        println("ans : ${ans} , guess : ${guess} , result : ${result}")
        for (i in 1..9){
            val guess = i
            val result = luckyNumber.validate(guess)
            println("ans : ${ans} , guess : ${guess} , result : ${result}")
            if(guess > ans){
                assertTrue(result>0)
            }else if(guess < 0){
                assertTrue(result<0)
            }else {
                assertTrue(result == 0)
            }
        }
    }
}