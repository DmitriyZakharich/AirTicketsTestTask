package com.example.air_tickets.domain.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.timeFormatting(pattern: String): String {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale("ru"))
    val date = Date(this)
    val result = simpleDateFormat.format(date)
    return if (result.contains('.')) {
        result.replace(".", "")
    } else {
        result
    }
}