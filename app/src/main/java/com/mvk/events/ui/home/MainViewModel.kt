package com.mvk.events.ui.home

import androidx.lifecycle.MutableLiveData
import com.mvk.events.data.model.Event
import com.mvk.events.data.repository.EventRepository
import com.mvk.events.ui.base.BaseViewModel
import com.mvk.events.utils.common.Resource
import com.mvk.events.utils.log.Logger
import com.mvk.events.utils.network.NetworkHelper
import com.mvk.events.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    eventRepository: EventRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading = MutableLiveData<Boolean>()
    val event = MutableLiveData<Resource<List<Event>>>()
    private var eventsList = HashMap<String, Event>()
    private var allEventList = mutableListOf<Event>()

    /**
     * Call the API and fetch the data when the activity starts
     */
    init {
        if (checkInternetConnectionWithMessage()) {
            loading.postValue(true)
            compositeDisposable.addAll(
                eventRepository.fetchEvents()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            eventsList = it.list.masterList
                            Logger.d("checkValue", eventsList.toString())
                            prepareData()
                            loading.postValue(false)
                            event.postValue(Resource.success(allEventList))
                        },
                        {
                            handleNetworkError(it)
                            loading.postValue(false)
                        }
                    )
            )
        }
    }

    override fun onCreate() {}

    /**
     * Process the data from the API
     */
    private fun prepareData() {
        val it: MutableIterator<MutableMap.MutableEntry<String, Event>> =
            eventsList.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next()
            allEventList.add(pair.value)
            println(pair.key + " = " + pair.value.name)
        }
    }
}