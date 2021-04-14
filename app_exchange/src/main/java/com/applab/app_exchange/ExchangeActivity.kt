package com.applab.app_exchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_exchange.*
import yahoofinance.YahooFinance
import java.text.SimpleDateFormat
import java.util.*

class ExchangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)
        // 接收 amount 資料
        val amount = intent.getIntExtra("amount", 0)
        // 接收 symbol 資料
        val symbol = intent.getStringExtra("symbol").toString()

        // 建立子執行緒
        Thread(Runnable {
            // 子執行緒環境中才能執行網路程式
            // YahooFinance API 是一種網路程式
            //val usd = getUSDByTWD(amount)
            val usd = getAmountByTWDAndSymbol(amount, symbol)

            // 變更 UI View 元件內容, 必須要在 UI 執行緒環境下
            runOnUiThread(Runnable {
                // UI 執行緒環境
                result_amount.text = "%.2f".format(usd)
                val time = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN)
                result_time.text = time.format(Date())
                text_symbol.text = "${text_symbol.text} ${symbol}"
            })

        }).start()


    }

    fun onClick(view: View) {
        finish()
    }
}