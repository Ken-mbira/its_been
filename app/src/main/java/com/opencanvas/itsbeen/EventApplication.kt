package com.opencanvas.itsbeen

import android.app.Application

class EventApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}