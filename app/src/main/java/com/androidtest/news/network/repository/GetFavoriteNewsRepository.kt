package com.androidtest.news.network.repository

import androidx.lifecycle.LiveData
import com.androidtest.news.cache.dao.FavoritesNewsDao
import com.androidtest.news.cache.model.FavoritEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class GetFavoriteNewsRepository @Inject constructor(
    private val favoritesNewsDao: FavoritesNewsDao,
) {


     fun getAllNews(): LiveData<List<FavoritEntity>> {
        return favoritesNewsDao.observeFavoriteNews()
    }

     fun insertNews(NewsEntity:FavoritEntity) {
        // insert all places to database
        CoroutineScope(Dispatchers.Default).launch {
            favoritesNewsDao.insert(NewsEntity)
        }
    }

}