package org.sopt.sopkathon.android3.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sopkathon.android3.data.model.response.ResponseGetStoneDetailDto
import org.sopt.sopkathon.android3.data.service.StoneApi

class DetailViewModel(private val api: StoneApi) : ViewModel() {

    private val _detailData = MutableLiveData<ResponseGetStoneDetailDto>()
    val detailData: LiveData<ResponseGetStoneDetailDto> = _detailData

    fun fetchDetailData(stoneId: Int) {
        viewModelScope.launch {
            try {
                val response = api.getStoneDetail(stoneId)
                _detailData.postValue(response)
            } catch (e: Exception) {
                // 에러 처리
            }
        }
    }
}