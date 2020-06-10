package com.mvk.events.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mvk.events.BuildConfig
import com.mvk.events.EventsApplication
import com.mvk.events.data.remote.NetworkService
import com.mvk.events.data.remote.Networking
import com.mvk.events.di.ApplicationContext
import com.mvk.events.utils.network.NetworkHelper
import com.mvk.events.utils.rx.RxSchedulerProvider
import com.mvk.events.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: EventsApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}