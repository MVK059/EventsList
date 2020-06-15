package com.mvk.events.utils.common

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    fun convertDate(dateTime: String): String {
        val date = Date(dateTime.toLong() * 1000L)
        val format: DateFormat = SimpleDateFormat("dd-MM-yyyy KK:mm a", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        return format.format(date)
    }
}