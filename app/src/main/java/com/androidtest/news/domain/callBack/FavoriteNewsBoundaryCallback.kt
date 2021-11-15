package com.androidtest.news.domain.callBack

import androidx.paging.PagedList
import com.androidtest.news.network.models.News
import com.androidtest.news.network.repository.GetFavoriteNewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteNewsBoundaryCallback @Inject constructor(
     private val newsRepository: GetFavoriteNewsRepository
) :

    PagedList.BoundaryCallback<News>() {

    var offset = 0

    override fun onZeroItemsLoaded() {}

    override fun onItemAtFrontLoaded(itemAtFront: News) {
    }

    override fun onItemAtEndLoaded(itemAtEnd: News) {

        CoroutineScope(Dispatchers.Default).launch {
            newsRepository.getAllNews()
        }
        offset += 50
    }
}