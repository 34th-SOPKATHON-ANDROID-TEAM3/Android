package org.sopt.sopkathon.android3.presentation.calendar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sopkathon.android3.data.ServicePool
import org.sopt.sopkathon.android3.data.model.response.ResponseGetCollectedStonesDto

class CalendarViewModel : ViewModel() {
    private val _rockList = MutableLiveData<List<ResponseGetCollectedStonesDto>>()
    val rockList: LiveData<List<ResponseGetCollectedStonesDto>>
        get() = _rockList

    private val stoneApi by lazy { ServicePool.stoneApi }
    fun fetchRockInfo() {
        viewModelScope.launch {
            runCatching {
                stoneApi.getCollectedStones()
            }.onSuccess {
                Log.e("리스트", it.toString())
                _rockList.value = it
            }.onFailure {
                Log.e("CalendarViewModel", it.message.toString())
            }
        }
    }
}