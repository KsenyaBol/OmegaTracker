package com.omegar.omegatracker.utils

const val FORMAT = "%d:%02d:00"
const val MINUTES_PER_HOUR = 60
const val START_TIME = "00:00:00"

fun Long?.timeFormat(): String {
    return this?.let {
        val hours = this / MINUTES_PER_HOUR
        val minutes = this % MINUTES_PER_HOUR
        String.format(FORMAT, hours, minutes)
    } ?: START_TIME
}