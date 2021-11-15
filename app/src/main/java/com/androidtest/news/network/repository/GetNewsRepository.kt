package com.androidtest.news.network.repository

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import com.androidtest.news.cache.dao.NewsDao
import com.androidtest.news.cache.model.NewsEntity
import com.androidtest.news.domain.util.Mapper
import com.androidtest.news.network.NewsService
import com.androidtest.news.utils.ApiKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetNewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val newsService: NewsService
) {

    private val mapper = Mapper()
    private var page = 4

    suspend fun fetchNews() {
        withContext(Dispatchers.IO) {
            try {
                var response = newsService.search(ApiKey, page).response.results
                insertAllNews(mapper.toNewsEntityList(response))
                page += 1
            }catch (e:NetworkErrorException){
                Log.d("HelloTag", e.message!!)
            }
        }
    }

     fun getAllNews(): LiveData<List<NewsEntity>> {
        return newsDao.observeNews()
    }

     fun insertAllNews(newsListEntity:List<NewsEntity>) {
        // insert all places to database
        CoroutineScope(Dispatchers.Default).launch {
            newsDao.insertNews(newsListEntity)
        }
    }

     fun deleteAllNews() {
        CoroutineScope(Dispatchers.Default).launch {
            newsDao.deleteAll()
        }
    }

    fun update(id:String) {
        CoroutineScope(Dispatchers.Default).launch {
            newsDao.update(true,id)
        }
    }
}