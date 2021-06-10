package com.sample.headlinesbyjusassignment.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

// ktlint-disable no-wildcard-imports

object DateFormatter {

    @SuppressLint("NewApi")
    fun dateFormatter(date: String): String {
        var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        val newDate: Date = format.parse(date)
        format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(newDate)
    }

    fun currentDate(): String {
        val todayDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(todayDate)
    }
}
