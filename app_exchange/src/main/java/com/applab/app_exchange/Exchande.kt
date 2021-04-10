package com.applab.app_exchange

import yahoofinance.YahooFinance

fun getUSDByTWD(amount : Int):Double{
    val rate = YahooFinance.get("TWDUSD=x").quote.price.toDouble()
    return amount * rate
}
fun getAmountByTWDAndSymbol(amount: Int,symbol : String):Double{
    val rate = YahooFinance.get("TWD${symbol}=x").quote.price.toDouble()
    return amount*rate
}