package org.sopt.sopkathon.android3.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sopkathon.android3.data.ServicePool
import org.sopt.sopkathon.android3.data.model.response.ResponseGetTodayStoneDto
import retrofit2.HttpException

class MainViewModel: ViewModel() {
    private val stoneApi by lazy { ServicePool.stoneApi }
    private val _stoneData = MutableLiveData<ResponseGetTodayStoneDto>()
    val stoneData : LiveData<ResponseGetTodayStoneDto> get() = _stoneData

    fun getTodayStone() = viewModelScope.launch {
        runCatching {
            stoneApi.getTodayStone()
        }.onSuccess {
            _stoneData.value = it
        }.onFailure {
            if(it is HttpException){
                Log.e("서버 에러", it.message())
            } else {
                Log.e("에러", it.message.toString())
            }
        }
    }
}