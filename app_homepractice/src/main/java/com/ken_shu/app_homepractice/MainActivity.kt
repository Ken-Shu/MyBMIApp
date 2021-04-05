package com.ken_shu.app_homepractice

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.ken_shu.app_homepractice.util.guessUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val an = guessUtil()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        an.setnumber()
    }

    fun onClockButton(view: View) {
        //要在buildgradle 上方plugins 加上
        //  id 'kotlin-android-extensions
        //    才可抓取 activity_main 內的 id
        val guess = editTextNumberSigned.text.toString().toInt()
        val result = an.getresult(guess)
        textView2.text="${an.getnumber()}"
        textView3.text = "${result.toString()}"
        editTextNumberSigned.selectAll()
        if (guess == an.getnumber()) {
            AlertDialog.Builder(this)
                .setTitle(R.string.minAndmax)
                .setMessage(R.string.pass)
                .setPositiveButton(R.string.OK, posit)
                //寫入.show 才會顯示視窗
                .show()

        }
    }
val posit = DialogInterface.OnClickListener { dialog, which ->
    when(which){
        DialogInterface.BUTTON_POSITIVE->{
          finish()
}
            }
        }

}