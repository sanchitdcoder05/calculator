package com.example.calculator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {
    // MutableStateFlow to hold the sound state
    private val _soundEnabled = MutableStateFlow(false)

    // Public StateFlow to expose the sound state
    val soundEnabled: StateFlow<Boolean> = _soundEnabled

    // Function to toggle the sound setting
    fun toggleSound(enabled: Boolean) {
        _soundEnabled.value = enabled
    }
}
