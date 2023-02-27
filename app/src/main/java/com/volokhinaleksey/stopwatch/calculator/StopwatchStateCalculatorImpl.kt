package com.volokhinaleksey.stopwatch.calculator

import com.volokhinaleksey.stopwatch.state.StopwatchState
import com.volokhinaleksey.stopwatch.timeprovider.TimeProvider

class StopwatchStateCalculatorImpl(
    private val timestampProvider: TimeProvider<Long>,
    private val timeCalculator: TimeCalculator<StopwatchState.Running>
) : StopwatchStateCalculator {

    override fun calculateRunningState(oldState: StopwatchState): StopwatchState.Running =
        when (oldState) {
            is StopwatchState.Paused -> {
                StopwatchState.Running(
                    startTime = timestampProvider.getMillis(),
                    elapsedTime = oldState.elapsedTime
                )
            }
            is StopwatchState.Running -> oldState
        }

    override fun calculatePauseState(oldState: StopwatchState): StopwatchState.Paused =
        when (oldState) {
            is StopwatchState.Paused -> oldState
            is StopwatchState.Running -> {
                val elapsedTime = timeCalculator.calculate(oldState)
                StopwatchState.Paused(elapsedTime = elapsedTime)
            }
        }

}