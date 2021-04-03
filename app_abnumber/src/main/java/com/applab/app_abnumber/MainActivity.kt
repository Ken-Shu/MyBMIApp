package com.applab.app_abnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    fun onCLickSubmitButton(view : View){
        val guess = inputNumber.text.toString()
        val result = ab.getresult(guess)
        Log.d(TAG, Arrays.toString(result))
        //result 資料呈現
        val log = "${result[0]} A ${result[1]} B "
        //log 資料累計
        resultLog.text ="${guess} ->$log \n${resultLog.text}"
        inputNumber.selectAll()
        if(result[0]==4){
            AlertDialog.Builder(this).setTitle(R.string.result).
        }
    }
}