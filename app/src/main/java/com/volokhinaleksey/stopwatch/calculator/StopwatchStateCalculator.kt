package com.volokhinaleksey.stopwatch.calculator

import com.volokhinaleksey.stopwatch.state.StopwatchState

interface StopwatchStateCalculator {

    fun calculateRunningState(oldState: StopwatchState): StopwatchState.Running
    fun calculatePauseState(oldState: StopwatchState): StopwatchState.Paused

}