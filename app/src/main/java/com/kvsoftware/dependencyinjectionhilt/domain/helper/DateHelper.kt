package com.kvsoftware.dependencyinjectionhilt.domain.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun toDateString1(date: Date): String {
        val formatter = SimpleDateFormat("MMM dd yyyy, HH:mm", Locale.getDefault())
        return formatter.format(date)
    }
}