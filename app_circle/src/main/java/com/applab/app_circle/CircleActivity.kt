package com.applab.app_circle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_circle.*

class CircleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle)

        btn_submit.setOnClickListener {
            val r = edit_r.text.toString().toInt()
            intent.putExtra("r",r)
            setResult(101 , intent)
            finish()
        }

    }
}