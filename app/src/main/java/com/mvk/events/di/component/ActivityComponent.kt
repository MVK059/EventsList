package com.mvk.events.di.component

import com.mvk.events.di.ActivityScope
import com.mvk.events.di.module.ActivityModule
import com.mvk.events.ui.home.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}