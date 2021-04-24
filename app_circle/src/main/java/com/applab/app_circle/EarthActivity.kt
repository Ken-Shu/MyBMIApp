package com.applab.app_circle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_earth.*

class EarthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earth)

        btn_submit.setOnClickListener {
            val r = edit_r.text.toString().toInt()
            intent.putExtra("r",r)
            setResult(201 , intent)
            finish()
        }
    }
}