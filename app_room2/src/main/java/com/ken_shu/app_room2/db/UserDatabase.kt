package com.ken_shu.app_room2.db

import androidx.room.Database
import androidx.room.RoomDatabase

//version 版本號
@Database (entities = [User :: class] , version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao


}