package com.example.weatherapp.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun getDayOfMonthString(
    dateStr: String,
    format: String = "yyyy-MM-dd HH:mm:ss",
    locale: Locale = Locale.getDefault()
): String? {
    try {
        val cal = Calendar.getInstance()
        cal.time = dateStr.toDate(format, locale) ?: Date()
        return cal.time.formatDate("EEEE", locale)
    } catch (e: Exception) {
        return null
    }
}

fun String.toDate(
    pattern: String = "yyyy-MM-dd HH:mm:ss",
    locale: Locale = Locale.ENGLISH,
    timeZone: TimeZone = TimeZone.getDefault(),
): Date? {
    return try {
        SimpleDateFormat(pattern, locale).apply { this.timeZone = timeZone }.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun Any.formatDate(
    pattern: String = "yyyy-MM-dd",
    locale: Locale = Locale.ENGLISH,
    timeZone: TimeZone = TimeZone.getDefault(),
): String? {
    return try {
        SimpleDateFormat(pattern, locale).apply { this.timeZone = timeZone }.format(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.isTheSameDay(comparedDate: Date): Boolean {
    val calendar = Calendar.getInstance()
    calendar.withTime(this)
    val comparedCalendarDate = Calendar.getInstance()
    comparedCalendarDate.withTime(comparedDate)
    return calendar.get(Calendar.DAY_OF_YEAR) == comparedCalendarDate.get(Calendar.DAY_OF_YEAR) && calendar.get(
        Calendar.YEAR
    ) == comparedCalendarDate.get(Calendar.YEAR)
}

fun Calendar.withTime(date: Date) {
    clear()
    time = date
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}