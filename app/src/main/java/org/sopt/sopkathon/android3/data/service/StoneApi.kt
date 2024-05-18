package org.sopt.sopkathon.android3.data.service

import org.sopt.sopkathon.android3.data.model.request.RequestPatchAnswerDto
import org.sopt.sopkathon.android3.data.model.response.ResponseGetCollectedStonesDto
import org.sopt.sopkathon.android3.data.model.response.ResponseGetStoneDetailDto
import org.sopt.sopkathon.android3.data.model.response.ResponseGetTodayStoneDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface StoneApi {
    @GET("stones/today")
    suspend fun getTodayStone(): ResponseGetTodayStoneDto
    @PATCH("stones/today")
    suspend fun patchAnswer(
        @Body requestPatchAnswerDto: RequestPatchAnswerDto
    )
    @GET("stones")
    suspend fun getCollectedStones(
    ): List<ResponseGetCollectedStonesDto>
    @GET("stones")
    suspend fun getStoneDetail(
        @Path("stoneId") stoneId: Int
    ): ResponseGetStoneDetailDto
}