package com.androidtest.news.cache.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsEntity(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "sectionName")
    val sectionName : String,

    @ColumnInfo(name = "webPublicationDate")
    val webPublicationDate : String,

    @ColumnInfo(name = "webTitle")
    val webTitle : String,

    @ColumnInfo(name = "webUrl")
    val webUrl : String,

    @ColumnInfo(name = "thumbnail")
    val thumbnail : String? = " ",

    @ColumnInfo(name = "favorite")
    val favorite : Boolean? = false
)