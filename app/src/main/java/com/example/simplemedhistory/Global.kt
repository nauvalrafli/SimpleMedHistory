package com.example.simplemedhistory

import com.example.simplemedhistory.data.MedDB

object Global {
    var userId = 0
    var username = ""
    var dataId = -1

    private val medDatabase = MedDB.getDatabase(SimpleMed.getAppsContext())
    val medDAO = medDatabase.getMedDAO()
}