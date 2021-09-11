package com.example.simplemedhistory.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Account", indices = [Index(value = ["id", "username"], unique = true)])
class Account(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "username")val username : String,
    @ColumnInfo(name = "password")val password : String
){
    constructor(username : String, password: String) : this(0, username, password)
}