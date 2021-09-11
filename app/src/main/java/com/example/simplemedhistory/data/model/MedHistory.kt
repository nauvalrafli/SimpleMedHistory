package com.example.simplemedhistory.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "medhistory", indices = [Index(value = ["id"], unique = true)])
class MedHistory(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")val id : Int,
    @ColumnInfo(name = "Userid")val userid : Int,
    @ColumnInfo(name = "disease")val disease : String,
    @ColumnInfo(name = "from")val from : String,
    @ColumnInfo(name = "until")val until : String,
    @ColumnInfo(name = "loc")val location : String,
    @ColumnInfo(name = "checkResult")val checkResult : String
) {
    constructor(userid: Int, disease: String, from: String, until: String, location: String, checkResult: String)
            : this(0, userid, disease, from, until, location, checkResult)
}
