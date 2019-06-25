package com.strikalov.indexfeargreed

import android.app.Application
import com.strikalov.indexfeargreed.di.AppComponent
import com.strikalov.indexfeargreed.di.AppModule
import com.strikalov.indexfeargreed.di.DaggerAppComponent
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric



class App : Application() {

    companion object {

        private lateinit var appComponent: AppComponent

        fun getAppComponent() = appComponent

    }

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        appComponent = generateAppComponent()
    }

    private fun generateAppComponent(): AppComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .build()
}