package com.volokhinaleksey.stopwatch.app

import android.app.Application
import com.volokhinaleksey.stopwatch.di.mainScreen
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(mainScreen))
        }
    }

}