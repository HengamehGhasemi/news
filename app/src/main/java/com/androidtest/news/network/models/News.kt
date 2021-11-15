package com.androidtest.news.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class News (

    @SerializedName("id")
    val id : String,

    @SerializedName("type")
    val type : String,

    @SerializedName("sectionName")
    val sectionName : String,

    @SerializedName("webPublicationDate")
    val webPublicationDate : String,

    @SerializedName("webTitle")
    val webTitle : String,

    @SerializedName("webUrl")
    val webUrl : String,

    @SerializedName("fields")
    val fields : Fields?,

    @SerializedName("favorite")
    val favorite : Boolean? = false

)