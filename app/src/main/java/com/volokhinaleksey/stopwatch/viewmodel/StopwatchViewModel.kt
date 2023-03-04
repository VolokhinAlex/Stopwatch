package com.volokhinaleksey.stopwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.volokhinaleksey.stopwatch.state.StopwatchStateHolder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class StopwatchViewModel(
    private val stopwatchStateHolder: StopwatchStateHolder
) : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _ticker: MutableStateFlow<String> = MutableStateFlow("00:00:000")
    val ticker: LiveData<String> = _ticker.asLiveData()
    private var job: Job? = null

    fun start() {
        if (job == null) startJob()
        stopwatchStateHolder.start()
    }

    fun pause() {
        stopwatchStateHolder.pause()
        stopJob()
    }

    fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        _ticker.value = "00:00:000"
    }

    private fun startJob() {
        viewModelScope.launch {
            while (isActive) {
                _ticker.value = stopwatchStateHolder.getStringTime()
                delay(20)
            }
        }
    }

    private fun stopJob() {
        viewModelScope.coroutineContext.cancelChildren()
        job = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
        job = null
    }

}