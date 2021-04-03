package com.applab.app_luckynumber

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.applab.app_luckynumber.utils.LuckyNumber
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var luckyNumber = LuckyNumber()
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,luckyNumber.ans.toString())
    }

    //按下 submit button 後要執行的邏輯
    //View 代表包含 activity_main 內的所有物件
    fun onClickSubmitButton(view: View) {
        //這裡的 text 抓到的是 Editalbe 可編輯的物件 轉成String 再轉成 int
        val guess = inputNumber.text.toString().toInt()
        Log.d(TAG,guess.toString())
        //val ans = luckyNumber.ans
        val result = luckyNumber.validate(guess)
        Log.d(TAG,result.toString())
        if (result > 0) {
            //通知(提示訊息) Toast
            //LENGTH_SHORT 通知 短時間
            //將文字寫在res 檔內 預讀取 皆使用 R.(型別).key
            Toast.makeText(this, R.string.too_big, Toast.LENGTH_SHORT).show()

            // 通知視窗 AlertDialog
            AlertDialog.Builder(this)
                .setTitle(R.string.title)
                .setMessage(R.string.too_big)
                .setPositiveButton(R.string.ok, listener2)
                .show()

        } else if (result < 0) {

            Toast.makeText(this, R.string.too_small, Toast.LENGTH_SHORT).show()

            AlertDialog.Builder(this)
                .setTitle(R.string.title)
                .setMessage(R.string.too_small)
                .setPositiveButton(R.string.ok, listener2)
                .show()

        } else {

            Toast.makeText(this, R.string.bingo, Toast.LENGTH_SHORT).show()

            AlertDialog.Builder(this)
                .setTitle(R.string.title)
                .setMessage(R.string.bingo)
                //positivebutton 放在最右邊
                .setPositiveButton(R.string.replay, listener)
                    //neutral 放在最左邊
                //.setNeutralButton(R.string.cancel, null)
                    //negativebutton 放右邊靠左
                .setNegativeButton(R.string.exit, listener)
                .show()
        }
    }
    //實作 AlerDialog 的 onClick 監聽器
    //通知視窗的監聽器 onclickListener {丟入 dialog 物件 which 哪一個 按鈕}
    val listener = DialogInterface.OnClickListener{dialog, which ->
        when(which) {
            DialogInterface.BUTTON_POSITIVE -> {
                //重新產生luckyNumber 物件
                luckyNumber = LuckyNumber()
            }
            DialogInterface.BUTTON_NEGATIVE -> {
                //finish() // Activity 運行結束
                finish()
            }
        }
    }
    val listener2 = DialogInterface.OnClickListener{ dialog, which ->
        inputNumber.selectAll()
    }
}