package org.sopt.sopkathon.android3.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetCollectedStonesDto(
    @SerialName("id")
    val id: Int,
    @SerialName("isPretty")
    val isPretty: Boolean,
    @SerialName("stoneImage")
    val stoneImage: String,
)