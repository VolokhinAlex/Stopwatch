package com.volokhinaleksey.stopwatch.formatter

class TimestampMillisecondsFormatter {

    fun format(timestamp: Long): String {
        val millisFormatted = (timestamp % 1000).toString().padStart(3, '0')
        val seconds = timestamp / 1000
        val secondsFormatted = (seconds % 60).toString().padStart(2, '0')
        val minutes = seconds / 60
        val minutesFormatted = (minutes % 60).toString().padStart(2, '0')
        val hours = minutes / 60
        return if (hours > 0) {
            val hoursFormatted = (hours % 60).toString().padStart(2, '0')
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted:$millisFormatted"
        }
    }

    companion object {
        private const val DEFAULT_FORMAT = "00:00:000"
    }

}