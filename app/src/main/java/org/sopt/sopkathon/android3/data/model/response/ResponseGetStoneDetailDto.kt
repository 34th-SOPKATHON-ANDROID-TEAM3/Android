package org.sopt.sopkathon.android3.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetStoneDetailDto(
    @SerialName("answer")
    val answer: String,
    @SerialName("id")
    val id: Int,
    @SerialName("question")
    val question: String,
    @SerialName("stoneImage")
    val stoneImage: String
)