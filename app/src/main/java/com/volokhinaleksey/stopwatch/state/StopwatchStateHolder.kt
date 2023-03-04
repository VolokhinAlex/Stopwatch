package com.volokhinaleksey.stopwatch.state

import com.volokhinaleksey.stopwatch.calculator.StopwatchStateCalculator
import com.volokhinaleksey.stopwatch.calculator.TimeCalculator
import com.volokhinaleksey.stopwatch.formatter.TimestampMillisecondsFormatter

interface StopwatchStateHolder {
    fun start()
    fun pause()
    fun stop()
    fun getStringTime(): String
}

class StopwatchStateHolderImpl(
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: TimeCalculator<StopwatchState.Running>,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatter
) : StopwatchStateHolder {

    var currentState: StopwatchState = StopwatchState.Paused(elapsedTime = 0)
        private set

    override fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    override fun pause() {
        currentState = stopwatchStateCalculator.calculatePauseState(currentState)
    }

    override fun stop() {
        currentState = StopwatchState.Paused(elapsedTime = 0)
    }

    override fun getStringTime(): String {
        val formattedTime = when (val state = currentState) {
            is StopwatchState.Paused -> state.elapsedTime
            is StopwatchState.Running -> elapsedTimeCalculator.calculate(state)
        }
        return timestampMillisecondsFormatter.format(formattedTime)
    }
}