package com.volokhinaleksey.stopwatch.calculator

import com.volokhinaleksey.stopwatch.state.StopwatchState

interface TimeCalculator<T : StopwatchState> {

    fun calculate(state: T): Long

}