package com.applab.app_luckyindex

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ComponentActivity() {
    val TAG = "MainActivity"
    //context 環境資訊 代表 這個 activity 的 環境物件
    lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
    }

    fun onClick(view: View) {
        Log.d(TAG, input_number.text.toString())
        val intent = Intent(context, resultActivity::class.java)
        // 將 input_number 所輸入的資料傳送到下一個 Activity (也就是 ResultActivity)
        intent.putExtra("number", input_number.text.toString())
        openResultActivityCustom.launch(intent)

    }

    private val openResultActivityCustom =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 101) {
                AlertDialog.Builder(this)
                    .setTitle(R.string.app_result_title)
                    .setMessage(result.data?.getStringExtra("luckyIndex").toString())
                    .setPositiveButton("OK",null)
                    .show()
                //Toast.makeText(this, result.data?.getStringExtra("luckyIndex").toString(), Toast.LENGTH_SHORT).show()
            }
        }

}