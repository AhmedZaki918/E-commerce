package com.silkysys.umco.data.local

import androidx.room.*
import com.silkysys.umco.data.model.auth.login.Data

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(data: Data)

    @Query("SELECT * FROM User")
    suspend fun getUser(): Data

    @Query("DELETE FROM User")
    suspend fun deleteUser()
}