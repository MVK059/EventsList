package com.mvk.events.ui.home.event

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.mvk.events.data.model.Event
import com.mvk.events.data.model.EventList
import com.mvk.events.ui.base.BaseAdapter

class EventAdapter(
    parentLifecycle: Lifecycle,
    events: ArrayList<Event>
) : BaseAdapter<Event, EventItemViewHolder>(parentLifecycle, events) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventItemViewHolder(parent)

}