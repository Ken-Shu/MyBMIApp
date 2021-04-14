package com.ken_shu.app_hw_stock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_stock.*

class NewStockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_stock)
        val number1 = intent.getStringExtra("number").toString()

            Thread(Runnable {
                val usd = getconpanyNumber(number1.toString())
                runOnUiThread(Runnable {
                    stock_Price.text = "${usd}"
                    stock_Number.text="${number1}"
                })
            }).start()

    }
    fun onClockF(view : View){
        finish()
    }
}