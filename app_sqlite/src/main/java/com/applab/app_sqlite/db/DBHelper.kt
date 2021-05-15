package com.applab.app_sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.applab.app_sqlite.models.Student
import java.lang.Exception
import java.util.Date

//在繼承 SQLiteOpenHelper 前 先寫下方物件 再丟進去裡面
//撰寫與資料庫 建立 升級 銷毀 CRUD 相關程序
class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    //在kotlin 只有單一物件時使用 companion object
    //SingleTon (companion object 相當於 java 的 static inner class 靜態內部類別)
    companion object {
        val DATABASE_VERSION = 1      //資料庫版本
        val DATABASE_NAME = "MyDB.db" //資料庫名稱

        //建立資料表 Student 的 SQL 語句
        val SQL_CREATE_STUDENT = "" +
                "CREATE TABLE Student(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," + //KEY AUTOINCREMENT 代表自動生成
                "name TEXT," +
                "score INTEGER" +
                "ct BIGINT)" //SQL 沒有LONG 對應的是 BIGINT

        // 刪除資料表 Student 的 SQL 語句
        val SQL_DELETE_STUDENT =
            "DROP TABLE IF EXISTS Student" // IF EXISTS 這一定要下 (原句:假設(DROP TABLE)的(Student)資料存在 就刪除)

    }

    /*****************************************
     * 上方 : SQLiteOpenHelper 系統會根據參數
     * 建立一個 SQLiteDatabase (容器(空間)) 還沒有 Table
     */

    //預設資料表建立
    override fun onCreate(db: SQLiteDatabase?) {
        //db 指的是 MyDB.db 資料庫容器
        db?.execSQL(SQL_CREATE_STUDENT) // 下達在 SQLiteDatabase(容器)內 建立一個 STUDENT 資料表
    }

    //資料庫升級
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_STUDENT) //升級 先刪除 資料表
        onCreate(db) //再重新建立 資料表
    }

    //新增資料
    fun createStudent(name: String, score: Int) {
        //取的資料庫
        val db = writableDatabase
        //準備要新增的紀錄
        val values = ContentValues()
        values.put("name", name)
        values.put("score", score)
        values.put("ct", Date().time)
        //新增到資料庫
        //nullColumnHack 是指若欄位是空白要放的內容物為何
        //action 異動筆數 (此次影響資料表的紀錄數量)
        //此次新增的 id 值
        val action = db.insert("Student", null, values)
        Log.d("DB", "createStudent : action = " + action)
    }

    //修改資料
    fun updateStudent(student: Student) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("id", student.id)
        values.put("name", student.name)
        values.put("score", student.score)
        values.put("ct", student.ct)
        //Where 條件
        val selection = "id = ? "
        //參數內容 代表上方問號
        val selectionArgs = arrayOf(student.id.toString())
        val action = db.update("Student", values, selection, selectionArgs)
        db.close()
        Log.d("DB", "updateStudent : action = " + action)
    }

    //刪除資料
    fun deleteStudent(id: Int) {
        val db = writableDatabase
        val selection = "id Like ?"
        val selectionArgs = arrayOf(id.toString())
        val action = db.delete("Student", selection, selectionArgs)
        db.close()
        Log.d("DB", "deleteStudent : action = " + action)
    }

    //查詢資料
    fun readAllStudent(): List<Student> {
        val students = ArrayList<Student>()
        val db = readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select id , name , score , ct from Student", null)
            if (cursor!!.moveToFirst()) { // 將查詢指標移動到第一筆
                while (cursor.isAfterLast == false) { //當指標不是最後一筆的時候
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val score = cursor.getInt(cursor.getColumnIndex("score"))
                    val ct = cursor.getLong(cursor.getColumnIndex("ct"))
                    val student = Student(
                        id, name, score, ct
                    )
                    students.add(student)
                    //每次做完之後 cursor 移動到下一筆
                    cursor.moveToNext()
                }
            }
        }catch (e: Exception) {
          e.printStackTrace()
            }

        db.close()
        return students
    }
}