package org.sopt.sopkathon.android3.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetTodayStoneDto(
    @SerialName("prettyImage")
    val prettyImage: String,
    @SerialName("question")
    val question: String,
    @SerialName("shakingImage")
    val shakingImage: String,
    @SerialName("shavingImage1")
    val shavingImage1: String,
    @SerialName("shavingImage2")
    val shavingImage2: String,
    @SerialName("uglyImage")
    val uglyImage: String,
)