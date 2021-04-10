package com.ken_shu.app_hw_exchange

import yahoofinance.YahooFinance


fun getUSDByTWD(amount : Int) : Double{
    var rate = YahooFinance.get("TWDUSD=x").quote.price.toDouble()
    return amount * rate
}
fun  getAmountByTWDAndSymbol(amount: Int , symbol : String): Double{
    var rate = YahooFinance.get("TWD${symbol}=x").quote.price.toDouble()
    return amount * rate
}
