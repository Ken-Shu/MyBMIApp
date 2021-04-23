package com.applab.app_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_main.view.*
import java.sql.Time

class AddActivity : AppCompatActivity() {
    var result_code = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        if(intent.getSerializableExtra("user") != null) {
            //Toast.makeText(this, intent.getSerializableExtra("user").toString(), Toast.LENGTH_SHORT).show()
            val user = intent.getSerializableExtra("user") as User
            et_name.setText(user.name.toString())
            et_score.setText(user.score.toString())
            button.setText("Update")
            result_code = 102
        }

    }

    fun onClick(view: View) {
        val name = et_name.text.toString()
        val score = et_score.text.toString()
        // 傳送資料到 MainActivity
        intent.putExtra("name", name)
        intent.putExtra("score", score)
        intent.getIntExtra("position", -1)
        setResult(result_code, intent)
        finish() // 將 ResultActivity 畫面關閉
    }


}