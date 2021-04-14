package com.ken_shu.app_hw_stock

import yahoofinance.YahooFinance

fun getconpanyNumber(number : String): String {
    val number = YahooFinance.get(number+".TW").quote.price.toString()

    return number
}