package com.applab.app_circle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        btn_circle.setOnClickListener{
                val intent = Intent(context , CircleActivity::class.java)
            //沒有回傳值得轉跳使用 startActivity 有回傳值得 就使用自己創建的 openResultActivityCustom
                //startActivity(intent)
            openResultActivityCustom.launch(intent)
        }
        btn_earth.setOnClickListener{
            val intent = Intent(context ,EarthActivity::class.java)
            //startActivity(intent)
            openResultActivityCustom.launch(intent)
        }
    }
    private val openResultActivityCustom = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        run {
            if (result.resultCode == 101) {
                var r = result.data?.getIntExtra("r", 0)
                val result = r!!.toDouble().pow(2.0) * Math.PI
                text_result.text = String.format("%,.2f",result)
            } else if (result.resultCode == 201) {
                var r = result.data?.getIntExtra("r", 0)
                val result = r!!.toDouble().pow(3.0) * Math.PI * (4.0/3.0)
                text_result.text =String.format("%,.2f", result)
            }
        }
    }

}