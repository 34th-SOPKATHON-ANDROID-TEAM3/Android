package org.sopt.sopkathon.android3.presentation.shake

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class ShakeViewModel : ViewModel() {
    private val _shakeState = MutableStateFlow<ShakeState>(ShakeState.BeforeShaking)
    val shakeState: StateFlow<ShakeState> = _shakeState.asStateFlow()

    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    private val _shakeFlag = MutableStateFlow<Boolean>(false)
    val shakeFlag: Flow<Boolean> = _shakeFlag.asStateFlow().debounce(3000)
    fun startShaking() = viewModelScope.launch {
        _shakeState.emit(ShakeState.UntilShaking)
    }

    fun endShaking() = viewModelScope.launch {
        _shakeState.emit(ShakeState.CompleteShaking)
    }

    fun addCount() = viewModelScope.launch {
        _count.emit(_count.value + 1)
    }

    fun finishAnimating() = viewModelScope.launch {
        _shakeState.emit(ShakeState.FinishShaking)
    }
}