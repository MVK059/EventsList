package com.mvk.events.data.model

import com.google.gson.annotations.SerializedName

data class EventList(
    @SerializedName("list")
    val list: EventMasterList
) {
    data class EventMasterList(
        @SerializedName("masterList")
        val masterList: HashMap<String, Event>
    )
}