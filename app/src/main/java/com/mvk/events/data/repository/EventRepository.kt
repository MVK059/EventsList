package com.mvk.events.data.repository

import com.mvk.events.data.model.EventList
import com.mvk.events.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchEvents(): Single<EventList> =
        networkService.doEventCall()
            .map {
                EventList(
                    it.list
                )
            }
}