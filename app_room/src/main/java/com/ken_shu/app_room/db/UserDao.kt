package com.ken_shu.app_room.db

import androidx.room.*

//Dao 是一個interface
//在room 裡面 它會自動幫你實作
//Data access ogject
@Dao
interface UserDao {
    //查詢單筆
    //這裡的 : uid 表示 方法參數
    @Query("SELECT * FROM User WHERE uid = :uid")
    fun getUser(uid : Int) :User

    //查詢所有 User
    //可以在 Query 裡面用查詢語句
    //但是他只會偵測@Entity
    @Query("SELECT * FROM user")
    fun getAllUsers() : List<User>

    //新增(含多筆批次)
    @Insert // vararg 相當於 java的 (int ...x) 自動變長參數
    fun insert (vararg user: User)

    //修改
    @Update
    fun update(user: User)

    //刪除
    @Delete
    fun delete(user: User)

    //另一種刪除
    @Query("DELETE FROM User WHERE uid = :uid")
    fun delete(uid: Int)
}