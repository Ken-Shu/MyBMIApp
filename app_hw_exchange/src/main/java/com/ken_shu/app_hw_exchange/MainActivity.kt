package com.ken_shu.app_hw_exchange

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        var symbols = arrayOf("USD", "JPY", "CNY", "AUD", "EUR")
        val adapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,symbols)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter=adapter
    }
    fun onClock(view : View){
        val intent = Intent(context ,ExchangeActivity:: class.java)
        intent.putExtra("amount",input_amount.text.toString().toInt())
        intent.putExtra("symbol",spinner.selectedItem.toString())
        startActivity(intent)
    }
}