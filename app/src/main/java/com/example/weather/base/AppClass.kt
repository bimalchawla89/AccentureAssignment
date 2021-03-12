package com.example.weather.base

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass:Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
        AndroidThreeTen.init(this)
    }

    companion object {
        const val TAG = "Weather"
        lateinit var appInstance: AppClass
            private set
    }
}