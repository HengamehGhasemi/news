package com.androidtest.news.cache.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidtest.news.cache.model.FavoritEntity

@Dao
interface FavoritesNewsDao {

    @Query("SELECT * FROM  favorite_table")
    fun getFavoritesNews(): DataSource.Factory<Int, FavoritEntity>

    @Query("SELECT * FROM  favorite_table")
    fun observeFavoriteNews(): LiveData<List<FavoritEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoritEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteNews(news: FavoritEntity?)

    @Query("DELETE  FROM favorite_table")
    fun deleteAll()
}