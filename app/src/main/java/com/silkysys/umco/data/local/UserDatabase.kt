package com.silkysys.umco.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.silkysys.umco.data.model.auth.login.Data

@Database(
    entities = [Data::class],
    version = 1, exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}