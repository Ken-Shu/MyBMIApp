package com.ken_shu.app_hw_exchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_exchange.*
import java.text.SimpleDateFormat
import java.util.*


class ExchangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)

        val amount = intent.getIntExtra("amount", 0)
        var symbol = intent.getStringExtra("symbol").toString()

        Thread(Runnable {
            var usd = getAmountByTWDAndSymbol(amount, symbol)
            runOnUiThread(Runnable {
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