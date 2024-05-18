package org.sopt.sopkathon.android3.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPatchAnswerDto(
    @SerialName("answer")
    val answer: String,
)
