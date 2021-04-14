package com.ken_shu.app_hw_stock

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
    }

fun oClock(view : View){
    val intent = Intent(context , NewStockActivity ::class.java)
    intent.putExtra("number",input_stock_number.text.toString())
    startActivity(intent)
}
}