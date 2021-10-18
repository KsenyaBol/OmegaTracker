package com.omegar.omegatracker.utils

private const val FORMAT = "%d:%02d:00"
private const val MINUTES_PER_HOUR = 60
private const val START_TIME = "00:00:00"

fun Long?.toTimeFormat(): String {
    return this?.let {
        val hours = this / MINUTES_PER_HOUR
        val minutes = this % MINUTES_PER_HOUR
        String.format(FORMAT, hours, minutes)
    } ?: START_TIME
}