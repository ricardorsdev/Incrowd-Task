package com.incrowdsports.task.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val apiDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private const val newDateFormat = "dd/MM/yyyy HH:mm"

    fun changeStringDateFormat(originalDate: String): String {
        val oldFormat = SimpleDateFormat(apiDateFormat, Locale.getDefault())
        val newFormat = SimpleDateFormat(newDateFormat, Locale.getDefault())

        try {
            oldFormat.parse(originalDate)?.let {
                return newFormat.format(it)
            } ?: kotlin.run {
                return originalDate
            }
        } catch (e: Exception) {
            return originalDate
        }
    }
}