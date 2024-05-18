package org.sopt.sopkathon.android3.presentation.question

sealed interface QuestionState<out T> {
    data class Success<T>(
        val data: T,
    ) : QuestionState<T>

    data class Failure(
        val msg: String,
    ) : QuestionState<Nothing>
}
