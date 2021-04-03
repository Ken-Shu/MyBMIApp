package com.applab.mybmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //在 Android 內 所有類別的最上層 都是 View
    //lateinit 表示 現在不宣告 之後在初始化
    //EditText 編輯文檔 在Layout 內的類別
    lateinit var h_view: EditText
    lateinit var w_view: EditText
    lateinit var result_view : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        h_view = findViewById<EditText>(R.id.amount)
        w_view = findViewById<EditText>(R.id.foot)
        result_view = findViewById<EditText>(R.id.result_chicken_amount)
        //每個物件都較 View 透過 ID 找到 tf_height
//        height = findViewById(R.id.tf_height)
//        weight = findViewById(R.id.tf_height)
    }

    //使用者按下 btn_calc 所要做的事情
    //View 固定要寫 指的是 按下Button的動作
    fun calc(view: View) {
        //使用者透過身高裡面的text(文檔) 轉成字串後 再轉成 Double
//        val h = findViewById<EditText>(R.id.tf_height).text.toString().toDouble()
//        val w = findViewById<EditText>(R.id.tf_weight).text.toString().toDouble()
        val h = h_view.text.toString().toDouble()
        val w = w_view.text.toString().toDouble()


        //找到 height 的資料 .text(文字資料) 轉成字串 再轉成 double
//        val h = height.text.toString().toDouble()
//        val w = weight.text.toString().toDouble()
        val bmi = w / Math.pow(h / 100, 2.0)
        //利用 Toast 顯示 bmi 的計算值
        //Toast LENGTH_SHORT 短時間顯示
        Toast.makeText(this, "%.2f".format(bmi), Toast.LENGTH_SHORT).show()
        result_view.text = "%.2f".format(bmi)
    }

}