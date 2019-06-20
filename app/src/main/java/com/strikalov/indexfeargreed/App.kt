package com.strikalov.indexfeargreed

import android.app.Application
import android.support.multidex.MultiDexApplication
import com.strikalov.indexfeargreed.di.AppComponent
import com.strikalov.indexfeargreed.di.AppModule
import com.strikalov.indexfeargreed.di.DaggerAppComponent

class App : MultiDexApplication() {

    companion object {

        private lateinit var appComponent: AppComponent

        fun getAppComponent() = appComponent

    }

    override fun onCreate() {
        super.onCreate()
        appComponent = generateAppComponent()
    }

    private fun generateAppComponent(): AppComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .build()
}