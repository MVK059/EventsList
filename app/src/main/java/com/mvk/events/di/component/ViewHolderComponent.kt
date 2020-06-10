package com.mvk.events.di.component

import com.mvk.events.di.ViewModelScope
import com.mvk.events.di.component.ApplicationComponent
import com.mvk.events.di.module.ViewHolderModule
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

}