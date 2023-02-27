package com.volokhinaleksey.stopwatch.di

import com.volokhinaleksey.stopwatch.calculator.ElapsedTimeCalculator
import com.volokhinaleksey.stopwatch.calculator.StopwatchStateCalculator
import com.volokhinaleksey.stopwatch.calculator.StopwatchStateCalculatorImpl
import com.volokhinaleksey.stopwatch.calculator.TimeCalculator
import com.volokhinaleksey.stopwatch.formatter.TimestampMillisecondsFormatter
import com.volokhinaleksey.stopwatch.state.StopwatchState
import com.volokhinaleksey.stopwatch.state.StopwatchStateHolder
import com.volokhinaleksey.stopwatch.state.StopwatchStateHolderImpl
import com.volokhinaleksey.stopwatch.timeprovider.TimeProvider
import com.volokhinaleksey.stopwatch.timeprovider.TimestampProvider
import com.volokhinaleksey.stopwatch.viewmodel.StopwatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreen = module {
    single<TimeProvider<Long>> {
        TimestampProvider()
    }
    single<TimeCalculator<StopwatchState.Running>> {
        ElapsedTimeCalculator(get())
    }
    single<StopwatchStateCalculator> { StopwatchStateCalculatorImpl(get(), get()) }
    single {
        TimestampMillisecondsFormatter()
    }
    single<StopwatchStateHolder> {
        StopwatchStateHolderImpl(get(), get(), get())
    }

    viewModel { StopwatchViewModel(get()) }
}