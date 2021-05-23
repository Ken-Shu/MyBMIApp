package com.ken_shu.app_room2

import android.content.Context
import android.content.DialogInterface
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ken_shu.app_room2.db.UserDatabase
import com.ken_shu.app_room2.db.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.cb_working
import kotlinx.android.synthetic.main.recyclerview_row.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowOnClickListener {
    lateinit var db: UserDatabase
    lateinit var context: Context
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        db = Room.databaseBuilder(context, UserDatabase::class.java, "mydb").build()
        GlobalScope.launch {
            var users = db.userDao().getAllUsers()
            if (users.size == 0) {
                db.userDao().insert(
                    User("John", 18, true),
                    User("Mary", 19, false)
                )
                users = db.userDao().getAllUsers()
            }
            //配置 recycler View
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
                adapter = recyclerViewAdapter
                //分隔線的設置
                val divider = DividerItemDecoration(context, VERTICAL)
                addItemDecoration(divider)
            }
            recyclerViewAdapter.setUsers(users as ArrayList<User>)
            //資料變更
            recyclerViewAdapter.notifyDataSetChanged()
        }
        //SAVE & Update
        btn_submit.setOnClickListener {
            Log.d("MainActivity","setOnClickListener")

            //進入 checkUserForm 判斷 並回傳給 setOnClickListener
            if (!checkUserForm()) return@setOnClickListener


            val name = et_name.text.toString()
            val age = et_age.text.toString().toInt()
            val working = cb_working.isChecked
            //新增的部分要放在 launch 裡面
            GlobalScope.launch {
                if (btn_submit.text.equals("Save")) {
                    val user = User(name, age, working)
                    db.userDao().insert(user)
                    reload()
                } else {  //修改
                    //從 et_name 的 tag 中 與取出 uid
                    val uid = et_name.getTag().toString().toInt()
                    val user = db.userDao().getUser(uid)
                    if (user != null) {
                        user.name = name
                        user.age = age
                        user.working = working
                        db.userDao().update(user)
                        reload()
                    }

                }
            }
        }
    }
    //按下 Save 時 進入 checkUserForm 裡面判斷 如果輸入框為 空值
    //則回傳 false
    fun checkUserForm() : Boolean{
        Log.d("MainActivity","CheckUserForm")
        if (et_name.text.toString().isEmpty()){
            return false
        }
        if (et_age.text.toString().isEmpty()){
            return false
        }
        return true
    }

    //資料重整
    fun reload() {
        Log.d("MainActivity","Reload")
        GlobalScope.launch {
            var users = db.userDao().getAllUsers()
            recyclerViewAdapter.setUsers(users as ArrayList<User>)
            //資料變更
            runOnUiThread {
                recyclerViewAdapter.notifyDataSetChanged()
                clear()
            }
        }
    }

    //清空
    fun clear() {
        Log.d("MainActivity","Clear")
        et_name.setText("")
        et_age.setText("")
        cb_working.isChecked = false
    }

    override fun onItemClickListener(user: User) {
        Log.d("MainActivity","OnItemClickLister")
        Toast.makeText(context, "Update" + user.toString(), Toast.LENGTH_SHORT).show()
        et_name.setText(user.name)
        //畫面上的 EditText 要放字串
        et_age.setText(user.age.toString())
        cb_working.isChecked = user.working
        //埋一個資料 tag 給修改用
        et_name.setTag(user.uid)
        //修改 button 上面的字
        btn_submit.setText("Update")
    }

    override fun onDeleteUserClickListener(user: User) {
        Log.d("MainActivity","OnDeleteUserClickListener")
        val listener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    GlobalScope.launch {
                        val delUser = db.userDao().getUser(user.uid!!.toInt())
                        if (delUser != null) {
                            db.userDao().delete(delUser.uid!!.toInt())
                        }
                        reload()
                    }
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                }
            }
        }
        AlertDialog.Builder(this)
            .setTitle("是否要刪除?")
            .setMessage(user.toString())
            .setPositiveButton("Delete", listener)
            .setNegativeButton("Cancel", null)
            .show()
    }
}
