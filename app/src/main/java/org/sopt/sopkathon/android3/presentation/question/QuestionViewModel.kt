package org.sopt.sopkathon.android3.presentation.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sopkathon.android3.data.ServicePool
import org.sopt.sopkathon.android3.data.model.request.RequestPatchAnswerDto
import retrofit2.HttpException

class QuestionViewModel : ViewModel() {
    private val answerService by lazy { ServicePool.stoneApi }
    private val _questionState = MutableLiveData<QuestionState<Unit>>()
    val questionState : LiveData<QuestionState<Unit>> get() = _questionState

    fun patchAnswer(answerText: String) = viewModelScope.launch {
        val requestDto = RequestPatchAnswerDto(answer = answerText)
        runCatching { answerService.patchAnswer(requestDto) }
            .onSuccess {
                _questionState.value = QuestionState.Success(it)
            }
            .onFailure {
                if(it is HttpException){
                    _questionState.value = QuestionState.Failure(it.code().toString())
                } else {
                    _questionState.value = QuestionState.Failure(it.message.toString())
                }
            }
    }
}