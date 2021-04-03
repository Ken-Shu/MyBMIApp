package com.applab.app_abnumber

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.AutoText
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.applab.app_abnumber.utils.ABNumber
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private var ab = ABNumber()
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ab.setAns()

    }

    fun onCLickSubmitButton(view: View) {
        val guess = inputNumber.text.toString()
        val result = ab.getresult(guess)
        Log.d(TAG, Arrays.toString(result))
        //result 資料呈現
        val log = "${result[0]} A ${result[1]} B "
        //log 資料累計
        abc.text = "${ab.getans()}"
        resultLog.text = "${guess} ->$log \n${resultLog.text}"
        inputNumber.selectAll()
        if (result[0] == 4) {
            AlertDialog.Builder(this)
                .setTitle(R.string.result)
                .setMessage(R.string.pass)
                .setPositiveButton(R.string.ok, listener)
                .setNeutralButton(R.string.next, listener)
                .show()

        }
    }

    val listener = DialogInterface.OnClickListener { dialog, which ->
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                finish()
            }
            DialogInterface.BUTTON_NEUTRAL -> {
                ab = ABNumber()
                ab.setAns()
                resultLog.text = ""
            }
        }
    }


}