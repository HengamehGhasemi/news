package com.androidtest.news.cache.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidtest.news.cache.model.NewsEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM  news_table")
    fun getNews(): DataSource.Factory<Int, NewsEntity>

    @Query("SELECT * FROM  news_table")
    fun observeNews(): LiveData<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(posts: List<NewsEntity>?)

    @Query("DELETE  FROM news_table")
    fun deleteAll()

    @Query("UPDATE news_table SET favorite=:fave WHERE id = :id")
    suspend fun update(fave: Boolean?, id: String)
}
