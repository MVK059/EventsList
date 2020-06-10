package com.mvk.events

import android.app.Application
import com.mvk.events.di.component.ApplicationComponent
import com.mvk.events.di.component.DaggerApplicationComponent
import com.mvk.events.di.module.ApplicationModule

class EventsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}