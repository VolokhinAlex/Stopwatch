package com.volokhinaleksey.stopwatch.timeprovider

class TimestampProvider : TimeProvider<Long> {

    override fun getMillis(): Long = System.currentTimeMillis()

}