package com.ken_shu.app_room

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.gsm.GsmCellLocation
import android.util.Log
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.github.javafaker.Faker
import com.ken_shu.app_room.db.User
import com.ken_shu.app_room.db.UserDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_form.*
import kotlinx.android.synthetic.main.user_form.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var db: UserDatabase
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        //建立資料庫
        db = Room.databaseBuilder(
            context
            , UserDatabase::class.java
            , "mydb"
        ).build()

        val user1 = User("John", 18, true)
        val user2 = User("Mary", 19, false)

        //db 的 UserDataBase 裡面有 userDao 方法
        //userDao 裡面 又有 insert 方法 再把 user1 , user2 丟進去資料庫裏面
        GlobalScope.launch {
            val users: List<User> = db.userDao().getAllUsers()
            Log.d("MainActivity", users.toString())
            if (users.size == 0) {
                db.userDao().insert(user1, user2)
            }
        }
        //輸入
        btn_insert.setOnClickListener {
            val faker = Faker()
            val name = faker.name().firstName()
            val age = Random.nextInt(30)
            val worker = Random.nextInt(2) == 0
            var user = User(name, age, worker)
            //子執行緒
            GlobalScope.launch {
                db.userDao().insert(user)
            }
            Toast.makeText(context, "Insert", Toast.LENGTH_SHORT).show()
        }

        //查詢
        btn_query.setOnClickListener {
            //子執行緒
            GlobalScope.launch {
                val users = db.userDao().getAllUsers()
                //將這段程式放在主執行緒執行
                runOnUiThread { view_result.setText(users.toString()) }
            }
        }
        //更新
        btn_update.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Update")
            //將xml 的東西 變成物件
            val dialoglayout = layoutInflater.inflate(R.layout.user_form, null)
            builder.setView(dialoglayout)

            //按下查詢
            dialoglayout.btn_searck.setOnClickListener {
                val uid = dialoglayout.et_uid.text.toString().toInt()
                GlobalScope.launch {
                    val user = db.userDao().getUser(uid)
                    if (user != null) {
                        runOnUiThread {
                            dialoglayout.et_name.setText(user.name.toString())
                            dialoglayout.et_age.setText(user.age.toString())
                            dialoglayout.check_working.isChecked = user.working == true
                        }
                    }
                }
            }
            //修改
            builder.setPositiveButton("Update") { dialog, which ->
//                val uid = dialoglayout.findViewById<EditText>(R.id.et_uid).text.toString().toInt()
//                val name = dialoglayout.findViewById<EditText>(R.id.et_name).text.toString()
//                val age = dialoglayout.findViewById<EditText>(R.id.et_age).text.toString().toInt()
                val uid = dialoglayout.et_uid.text.toString().toInt()
                val name = dialoglayout.et_name.text.toString()
                val age = dialoglayout.et_age.text.toString().toInt()
                val working = dialoglayout.check_working.isChecked
                GlobalScope.launch {
                    //抓取 user 的 第1筆資料
                    var user = db.userDao().getUser(uid)
                    if (user != null) {
                        user.name = name
                        user.age = age
                        user.working = working
                    }
                    db.userDao().update(user)
                }
            }
            builder.setNegativeButton("Cancle", null)
            builder.show()
        }
//            val faker = Faker()
//            val name = faker.name().firstName()
//            val age = Random.nextInt(30)
//            val worker = Random.nextInt(2)==0
//
//            //子執行緒
//            GlobalScope.launch {
//                //抓取 user 的 第1筆資料
//                var user = db.userDao().getUser(1)
//                if(user != null) {
//                    user.name = name
//                    user.age = age
//                    user.working = worker
//                }
//                db.userDao().update(user)
//            }
//            Toast.makeText(context,"Update",Toast.LENGTH_SHORT).show()

        //刪除
        btn_delete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete")
            val edittext = EditText(context)
            builder.setView(edittext)
            builder.setPositiveButton("DELETE") {
                //which 抓取 ID 值
                    dialog, which ->
                val uid = edittext.text.toString().toInt()
                GlobalScope.launch {
                    db.userDao().delete(uid.toInt())
                }

            }
            builder.setNegativeButton("Cancle", null)
            builder.show()
        }
    }
}
