package com.applab.app_luckyindex

import android.util.Log
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun luckyIndex_isCorrect() {
        print(getLuckyIndex(3).toString())
    }
}