package com.example.simplemedhistory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simplemedhistory.data.model.Account

@Dao
interface AccountDAO {
    @Query("SELECT * FROM Account WHERE username LIKE :x AND password LIKE :y")
    fun login(x : String, y:String) : List<Account>

    @Query("SELECT * FROM Account WHERE username LIKE :x")
    fun checkUsername(x : String) : List<Account>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun signUp(account : Account)
}