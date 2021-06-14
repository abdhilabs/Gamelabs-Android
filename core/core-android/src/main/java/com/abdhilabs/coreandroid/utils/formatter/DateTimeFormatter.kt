package com.abdhilabs.coreandroid.utils.formatter

import java.text.SimpleDateFormat
import java.util.*

object DateTimeFormatter {

    private val dateOutput = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("en", "EN"))

    fun getDateFromString(dateString: String): String {
        val date = parseDate(dateString)
        return dateOutput.format(date)
    }

    private fun parseDate(dateString: String): Date {
        val dateInput = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return dateInput.parse(dateString) ?: Date()
    }
}