package com.applab.app_exchange

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun exchange_isCorrect() {
        val twd =10000
        val usd = getUSDByTWD(twd)
        println("${twd} , ${usd}")
    }
}