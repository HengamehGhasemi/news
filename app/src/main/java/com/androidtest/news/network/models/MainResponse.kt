package com.androidtest.news.network.models

import com.google.gson.annotations.SerializedName

data class MainResponse (

    @SerializedName("response")
    val response : Response

)