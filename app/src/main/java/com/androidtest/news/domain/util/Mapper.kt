package com.androidtest.news.domain.util

import androidx.paging.DataSource
import com.androidtest.news.cache.model.FavoritEntity
import com.androidtest.news.cache.model.NewsEntity
import com.androidtest.news.network.models.Fields
import com.androidtest.news.network.models.News

class Mapper  {

     fun mapToEntityModel(model: News): NewsEntity {
        return NewsEntity(
            id = model.id,
            type = model.type,
            sectionName = model.sectionName,
            webPublicationDate = model.webPublicationDate,
            webTitle = model.webTitle,
            webUrl = model.webUrl,
            thumbnail = model.fields?.thumbnail,
            favorite = model.favorite
        )
    }

    fun mapToFavoriteEntityModel(model: News): FavoritEntity {
        return FavoritEntity(
            id = model.id,
            type = model.type,
            sectionName = model.sectionName,
            webPublicationDate = model.webPublicationDate,
            webTitle = model.webTitle,
            webUrl = model.webUrl,
            thumbnail = model.fields?.thumbnail!!
        )
    }

     fun mapToDomainModel(model: NewsEntity): News {
        return News(
            id = model.id,
            type = model.type,
            sectionName = model.sectionName,
            webPublicationDate = model.webPublicationDate,
            webTitle = model.webTitle,
            webUrl = model.webUrl,
            fields = Fields(model.thumbnail),
            favorite = model.favorite
        )
    }

    fun mapFavoriteToDomainModel(model: FavoritEntity): News {
        return News(
            id = model.id,
            type = model.type,
            sectionName = model.sectionName,
            webPublicationDate = model.webPublicationDate,
            webTitle = model.webTitle,
            webUrl = model.webUrl,
            fields = Fields(model.thumbnail),
            favorite = true
        )
    }

    fun toNewsEntityList(initial: List<News>): List<NewsEntity>{
        return initial.map { mapToEntityModel(it) }
    }

    fun toNewsList(initial: List<NewsEntity>): List<News>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toNewsDataSourceList(initial: DataSource.Factory<Int,NewsEntity>): DataSource.Factory<Int,News>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toFavoriteNewsDataSourceList(initial: DataSource.Factory<Int,FavoritEntity>): DataSource.Factory<Int,News>{
        return initial.map { mapFavoriteToDomainModel(it) }
    }
}