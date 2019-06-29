package com.mashup.tenSecond.ui

import android.app.Application
import com.mashup.tenSecond.di.appModules
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }
}