package org.sopt.sopkathon.android3.presentation.dummy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sopkathon.android3.data.ServicePool
import org.sopt.sopkathon.android3.data.model.response.ResponseDummyDto
import retrofit2.HttpException

class DummyViewModel : ViewModel() {
    private val dummyService by lazy { ServicePool.dummyService }

    private val _dummyState = MutableLiveData<DummyState<ResponseDummyDto>>()
    val dummyState: LiveData<DummyState<ResponseDummyDto>> get() = _dummyState

    fun getDummy() = viewModelScope.launch {
        runCatching { dummyService.getDummy(1) }
            .onSuccess {
                _dummyState.value = DummyState.Success(it)
            }
            .onFailure {
                if (it is HttpException) {
                    _dummyState.value = DummyState.Failure(it.code().toString())
                } else {
                    _dummyState.value = DummyState.Failure(it.message.toString())
                }
            }
    }
}