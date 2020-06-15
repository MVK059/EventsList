package com.mvk.events.ui.home.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mvk.events.data.model.Event
import com.mvk.events.data.model.EventList
import com.mvk.events.data.model.Image
import com.mvk.events.ui.base.BaseItemViewModel
import com.mvk.events.utils.network.NetworkHelper
import com.mvk.events.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class EventItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<Event>(schedulerProvider, compositeDisposable, networkHelper) {

    val name: LiveData<String> = Transformations.map(data) { it.name }
    val venueName: LiveData<String> = Transformations.map(data) { it.venueName }
    val showStartTime: LiveData<String> = Transformations.map(data) { it.showStartTime }
    val minPrice: LiveData<String> = Transformations.map(data) { it.minPrice }
    val coverImage: LiveData<Image> = Transformations.map(data) {
        it.coverImage.run { Image(this) }
    }

    override fun onCreate() {

    }
}