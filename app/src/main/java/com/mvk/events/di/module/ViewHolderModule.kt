package com.mvk.events.di.module

import androidx.lifecycle.LifecycleRegistry
import com.mvk.events.di.ViewModelScope
import com.mvk.events.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}