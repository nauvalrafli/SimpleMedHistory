package com.example.simplemedhistory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplemedhistory.data.model.Account

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AccountDB :RoomDatabase(){
    companion object {
        @Volatile
        private var INSTANCE : AccountDB? = null

        fun getDatabase(context: Context):AccountDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDB::class.java,
                    "account_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
    abstract fun getAccDAO() : AccountDAO
}