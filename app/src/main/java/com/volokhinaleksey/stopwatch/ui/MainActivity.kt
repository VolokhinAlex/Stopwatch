package com.volokhinaleksey.stopwatch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.volokhinaleksey.stopwatch.databinding.ActivityMainBinding
import com.volokhinaleksey.stopwatch.viewmodel.StopwatchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val stopwatchViewModel: StopwatchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        stopwatchViewModel.ticker.observe(this) {
            binding.stopwatchField.text = it
        }

        binding.startStopwatchBtn.setOnClickListener {
            stopwatchViewModel.start()
        }
        binding.pauseStopwatchBtn.setOnClickListener {
            stopwatchViewModel.pause()
        }
        binding.stopStopwatchBtn.setOnClickListener {
            stopwatchViewModel.stop()
        }
        setContentView(binding.root)
    }

}