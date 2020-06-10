package com.mvk.events.di.component

import com.mvk.events.di.ActivityScope
import com.mvk.events.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
}