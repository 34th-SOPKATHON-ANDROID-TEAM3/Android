package org.sopt.sopkathon.android3.presentation.dummy

sealed interface DummyState<out T>{
    data class Success<T>(
        val data: T,
    ) : DummyState<T>

    data class Failure(
        val msg: String,
    ) : DummyState<Nothing>
}
