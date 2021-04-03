package com.applab.app_abnumber

import com.applab.app_abnumber.utils.ABNumber
import org.junit.Test

import org.junit.Assert.*
import java.util.*

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
    fun testAB(){
        val ab = ABNumber()
        ab.setAns()
        println(Arrays.toString(ab.getresult("1234")))
    }
}