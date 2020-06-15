package com.mvk.events.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvk.events.data.repository.EventRepository
import com.mvk.events.ui.base.BaseActivity
import com.mvk.events.ui.home.MainViewModel
import com.mvk.events.utils.ViewModelProviderFactory
import com.mvk.events.utils.navigation.NavigationController
import com.mvk.events.utils.network.NetworkHelper
import com.mvk.events.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*, *>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideNavigationController(): NavigationController =
        NavigationController(activity, activity.supportFragmentManager)

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        eventRepository: EventRepository
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(schedulerProvider, compositeDisposable, networkHelper, eventRepository)
        }).get(MainViewModel::class.java)

}