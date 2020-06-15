package com.mvk.events.data.model

import com.google.gson.annotations.SerializedName

data class Event(

    @SerializedName("name")
    val name: String,

    @SerializedName("horizontal_cover_image")
    val coverImage: String,

    @SerializedName("venue_name")
    val venueName: String,

    @SerializedName("min_show_start_time")
    val showStartTime: String,

    @SerializedName("min_price")
    val minPrice: String
)