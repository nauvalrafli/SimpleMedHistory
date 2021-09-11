package com.example.simplemedhistory.data

import androidx.room.*
import com.example.simplemedhistory.data.model.MedHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface MedDAO {
    @Query("SELECT * FROM medhistory WHERE userid = :userid")
    fun getAll(userid : Int) : List<MedHistory>

    @Query("SELECT * FROM medhistory WHERE id = :id LIMIT 1")
    fun fetchDataWithId(id: Int) : MedHistory

    @Query("DELETE FROM medhistory WHERE id Like :id")
    fun delete(id : Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(medHistory: MedHistory)

    @Update
    fun update(medHistory: MedHistory)
}