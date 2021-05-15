package com.applab.app_sqlite_lucky.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.applab.app_sqlite_lucky.models.Lucky
import java.lang.Exception
import java.util.Date

// 撰寫與資料庫建立,升級,銷毀與CRUD相關程序
class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // SingleTon (companion object相當於 Java 的 static inner class 靜態內部類別)
    companion object {
        val DATABASE_VERSION = 1 // 資料庫版本
        val DATABASE_NAME = "MyLuckyDB.db" // 資料庫名稱

        // 建立資料表 Lucky 的 SQL 語句
        val SQL_CREATE_LUCKY = "" +
                "CREATE TABLE Lucky(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "color TEXT, " +
                "num INTEGER, " +
                "ct BIGINT)"
        // 刪除資料表 Lucky 的 SQL 語句
        val SQL_DELETE_LUCKY = "DROP TABLE IF EXISTS Lucky"

    }

    // 預設資料表的建立
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_LUCKY)
    }

    // 資料庫升級
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_LUCKY)
        onCreate(db)
    }

    // 新增資料
    fun createLucky(color: String, num: Int) {
        // 取得資料庫
        val db = writableDatabase
        // 準備要新增的紀錄
        val values = ContentValues()
        values.put("color", color)
        values.put("num", num)
        values.put("ct", Date().time)
        // 新增到資料庫
        // nullColumnHack 是指若欄位是空白要放的內容為何 ?
        // action 此次新增的 id 值
        val action = db.insert("Lucky", null, values)
        db.close()
        Log.d("DB", "createLucky: action=$action")

    }

    // 修改資料
    fun updateLucky(lucky: Lucky) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("id", lucky.id)
        values.put("color", lucky.color)
        values.put("num", lucky.num)
        values.put("ct", lucky.ct)
        // Where 條件
        val selection = "id LIKE ?"
        val selectionArgs = arrayOf(lucky.id.toString())
        // action 是指異動筆數
        val action = db.update("Lucky", values, selection, selectionArgs)
        db.close()
        Log.d("DB", "updateLucky: action=$action")
    }

    // 刪除資料
    fun deleteLucky(id: Int) {
        val db = writableDatabase
        val selection = "id LIKE ?"
        val selectionArgs = arrayOf(id.toString())
        val action = db.delete("Lucky", selection, selectionArgs)
        db.close()
        Log.d("DB", "deleteLucky: action=$action")
    }

    // 查詢資料
    fun readOdds(): List<Lucky> {
        val odds = ArrayList<Lucky>()
        val db = readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select id, color, num, ct from Lucky", null)
            if(cursor!!.moveToFirst()) { // 將查詢指標移動到第一筆
                while (!cursor.isAfterLast) { // 當查詢指標不是最後一筆時
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val color = cursor.getString(cursor.getColumnIndex("color"))
                    val num = cursor.getInt(cursor.getColumnIndex("num"))
                    val ct = cursor.getLong(cursor.getColumnIndex("ct"))
                    val lucky = Lucky(
                        id, color, num, ct
                    )
                    odds.add(lucky)
                    cursor.moveToNext()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        db.close()
        return odds
    }

}