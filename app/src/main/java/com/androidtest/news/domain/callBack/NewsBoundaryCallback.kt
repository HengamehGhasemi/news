package com.androidtest.news.domain.callBack

import androidx.paging.PagedList
import com.androidtest.news.network.models.News
import com.androidtest.news.network.repository.GetNewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsBoundaryCallback @Inject constructor(
     private val newsRepository: GetNewsRepository
) :

    PagedList.BoundaryCallback<News>() {

    var offset = 0

    override fun onZeroItemsLoaded() {
    }

    override fun onItemAtFrontLoaded(itemAtFront: News) {
    }

    override fun onItemAtEndLoaded(itemAtEnd: News) {

        CoroutineScope(Dispatchers.Default).launch {
            newsRepository.fetchNews()
        }
        offset += 50
    }
}