package com.androidtest.news.network.models

import com.google.gson.annotations.SerializedName

data class Response (

    @SerializedName("status")
    val status : String,

    @SerializedName("total")
    val total : Long,

    @SerializedName("startIndex")
    val startIndex : Long,

    @SerializedName("pageSize")
    val pageSize : Long,

    @SerializedName("currentPage")
    val currentPage : Long,

    @SerializedName("pages")
    val pages : Long,

    @SerializedName("results")
    val results : List<News>
)