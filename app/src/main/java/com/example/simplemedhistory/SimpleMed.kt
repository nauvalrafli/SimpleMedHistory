package com.example.simplemedhistory

import android.app.Application
import android.content.Context

var context : Context? = null

class SimpleMed : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        fun getAppsContext(): Context {
            return context!!
        }
    }
}