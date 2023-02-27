package com.volokhinaleksey.stopwatch.timeprovider

interface TimeProvider<T> {

    fun getMillis(): T

}