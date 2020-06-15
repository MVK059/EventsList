package com.mvk.events.ui.home.event

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mvk.events.R
import com.mvk.events.data.model.Event
import com.mvk.events.data.model.EventList
import com.mvk.events.di.component.ViewHolderComponent
import com.mvk.events.ui.base.BaseItemViewHolder
import com.mvk.events.utils.common.TimeUtils
import kotlinx.android.synthetic.main.item_view_event.view.*

class EventItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<Event, EventItemViewModel>(R.layout.item_view_event, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) =
        viewHolderComponent.inject(this)

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.name.observe(this, Observer {
            itemView.eventName.text = it
        })

        viewModel.venueName.observe(this, Observer {
            itemView.eventVenue.text = itemView.eventVenue.context.getString(R.string.venue, it)
        })

        viewModel.showStartTime.observe(this, Observer {
            itemView.eventStartTime.text =
                itemView.eventVenue.context.getString(R.string.time, TimeUtils.convertDate(it))
        })

        viewModel.minPrice.observe(this, Observer {
            itemView.eventMinPrice.text = itemView.eventVenue.context.getString(R.string.price, it)
        })

        viewModel.coverImage.observe(this, Observer {
            it?.run {
                val glideRequest = Glide
                    .with(itemView.eventImage.context)
                    .load(url)

                glideRequest.into(itemView.eventImage)
            }
        })
    }

}