package com.example.makeup.data.api

import com.example.makeup.constants.Constants.END_POINT
import com.example.makeup.domain.model.MakeupItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MakeupAPI {
    @GET(END_POINT)
    suspend fun getAllBrands(): Response<List<MakeupItem>>

    @GET(END_POINT)
    suspend fun getAllCategory(@Query("brand") brand: String): Response<List<MakeupItem>>

    @GET(END_POINT)
    suspend fun getAllProduct(@Query("category") category: String): Response<List<MakeupItem>>
}