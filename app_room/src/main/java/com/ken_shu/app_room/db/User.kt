package com.ken_shu.app_room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//使用Room之前 要先建立 Entity 對應資料庫
//Entity 表示 數據庫中的表
@Entity
data class User(
    @ColumnInfo(name = "name")var name: String,
    @ColumnInfo(name = "age")var age: Int,
    @ColumnInfo(name = "working")var working: Boolean
) {
    //資料庫主鍵
    //自動生成鍵值 autoGenerate = true 例 1 2 3 4 5
    @PrimaryKey(autoGenerate = true) var uid : Int? = null
}