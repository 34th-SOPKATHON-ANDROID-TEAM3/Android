package org.sopt.sopkathon.android3.presentation.shake

sealed interface ShakeState {
    data object BeforeShaking: ShakeState
    data object UntilShaking: ShakeState
    data object CompleteShaking: ShakeState
    data object FinishShaking: ShakeState
}