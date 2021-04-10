package com.applab.app_exchange

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        // 配置 spinner 的下拉選單資料
        val symbols = arrayOf("USD", "JPY", "CNY", "AUD", "EUR")
        // 適配器 adapter (管理資料與資料UI的配置)
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, symbols)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter

    }

    fun onClick(view: View) {
        val intent = Intent(context, ExchangeActivity::class.java)
        // 將 input_number 所輸入的資料傳送到下一個 Activity (也就是 ResultActivity)
        intent.putExtra("amount", input_amount.text.toString().toInt())
        intent.putExtra("symbol", spinner.selectedItem.toString())
        startActivity(intent)
    }
}