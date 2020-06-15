package com.mvk.events.data.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("_id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("horizontal_cover_image")
    val coverImage: String,

    @SerializedName("venue_name")
    val venueName: String,

    @SerializedName("min_show_start_time")
    val showStartTime: String
)