package com.example.simplemedhistory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplemedhistory.data.model.MedHistory
import java.security.AccessControlContext


@Database(entities = [MedHistory::class], version = 1)
abstract class MedDB : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: MedDB? = null

        fun getDatabase(context: Context): MedDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedDB::class.java,
                    "med_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getMedDAO() : MedDAO
}