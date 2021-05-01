package com.applab.app_recycler

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var context :Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        //讀取json文字檔
        var json = assets.open("csvjson .json").bufferedReader().use{ it.readText() }
        //將json 字串 轉成 List<Map<String , Object>>
        val listType : Type = object  : TypeToken<List<Map<String?, Object?>?>?>() {}.type
        val list : List<Map<String,Objects>> = Gson().fromJson(json,listType)

        Log.d("MainActivity",list.toString())
        //recycler_view 接收到 把上面轉好的 list 丟進去 SalesAdapter 裡面
        //在設置 adapter 專屬的監聽器
        recycler_view.adapter = SalesAdapter(list)
        recycler_view.layoutManager = LinearLayoutManager(this)

        //recycler 優化 (固定 item 尺寸)
        //僅用於每一個 item 固定樣板
        recycler_view.setHasFixedSize(true)


    }
}