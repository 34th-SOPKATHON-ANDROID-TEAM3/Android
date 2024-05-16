package org.sopt.sopkathon.android3.data.service

import org.sopt.sopkathon.android3.data.model.request.RequestDummyDto
import org.sopt.sopkathon.android3.data.model.response.ResponseDummyDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DummyService {
    @POST("post")
    suspend fun postDummy(
        requestDummyDto: RequestDummyDto,
    ): ResponseDummyDto

    @GET("get")
    suspend fun getDummy(
        @Query("query") query: Int,
    ): ResponseDummyDto
}