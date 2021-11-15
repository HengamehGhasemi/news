package com.androidtest.news.network

import com.androidtest.news.network.models.MainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("search")
    suspend fun search(
        @Query("api-key") api_key : String?,
        @Query("page") page: Int =1,
        @Query("page-size") page_size: Int = 20,
        @Query("show-fields") show_fields: String = "thumbnail",
    ): MainResponse
}