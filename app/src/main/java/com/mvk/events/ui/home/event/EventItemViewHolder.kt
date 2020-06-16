package com.mvk.events.ui.home.event

import android.os.Build
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mvk.events.R
import com.mvk.events.data.model.Event
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
            val textValue = itemView.eventVenue.context.getString(R.string.venue, it)
            setText(textValue, itemView.eventVenue)
        })

        viewModel.showStartTime.observe(this, Observer {
            val textValue =
                itemView.eventStartTime.context.getString(R.string.time,  TimeUtils.convertDate(it))
            setText(textValue, itemView.eventStartTime)
        })

        viewModel.minPrice.observe(this, Observer {
            val textValue = itemView.eventMinPrice.context.getString(R.string.price, it)
            setText(textValue, itemView.eventMinPrice)
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

    private fun setText(textValue: String, textView: TextView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text =  Html.fromHtml(textValue, Html.FROM_HTML_MODE_COMPACT);
        } else {
            textView.text =  Html.fromHtml(textValue);
        }
    }

}