package com.volokhinaleksey.stopwatch.calculator

import com.volokhinaleksey.stopwatch.state.StopwatchState
import com.volokhinaleksey.stopwatch.timeprovider.TimeProvider

class ElapsedTimeCalculator(
    private val timestampProvider: TimeProvider<Long>
) : TimeCalculator<StopwatchState.Running> {

    override fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMillis()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }

}