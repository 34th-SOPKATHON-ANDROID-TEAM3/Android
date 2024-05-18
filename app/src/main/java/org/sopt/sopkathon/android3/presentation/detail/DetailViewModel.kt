package org.sopt.sopkathon.android3.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sopkathon.android3.data.ServicePool
import org.sopt.sopkathon.android3.data.model.response.ResponseGetStoneDetailDto

class DetailViewModel : ViewModel() {
    private val service = ServicePool.stoneApi

    private val _detailData = MutableLiveData<ResponseGetStoneDetailDto>()
    val detailData: LiveData<ResponseGetStoneDetailDto> = _detailData

    fun fetchDetailData(stoneId: Int) {
        viewModelScope.launch {
            runCatching {
                service.getStoneDetail(stoneId)
            }.onSuccess {
                _detailData.value = it
            }.onFailure {
                Log.e("실패", it.message.toString())
            }
        }
    }
}