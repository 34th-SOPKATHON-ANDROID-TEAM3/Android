package org.sopt.sopkathon.android3.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetTodayStoneDto(
    @SerialName("prettyImage")
    val prettyImage: String,
    @SerialName("question")
    val question: String,
    @SerialName("uglyImage")
    val uglyImage: String,
    @SerialName("answer")
    val answer: String?,
)