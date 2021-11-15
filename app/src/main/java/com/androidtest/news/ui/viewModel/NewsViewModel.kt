package com.androidtest.news.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidtest.news.cache.dao.NewsDao
import com.androidtest.news.domain.callBack.NewsBoundaryCallback
import com.androidtest.news.domain.util.Mapper
import com.androidtest.news.network.models.News
import com.androidtest.news.network.repository.GetNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor (
    private val repository: GetNewsRepository,
    private val newsDao: NewsDao
) : ViewModel() {

    val mapper = Mapper()
    var callback: NewsBoundaryCallback? ? = null
    var factory: DataSource.Factory<Int, News> ? = null
    var config : PagedList.Config ? = null
    var news: LiveData<PagedList<News>> ? = null

    init {
        callback = NewsBoundaryCallback(repository)
        factory  = mapper.toNewsDataSourceList(newsDao?.getNews())
        config = PagedList.Config.Builder()
            .setPageSize(60)
            .setInitialLoadSizeHint(60)
            .setEnablePlaceholders(true)
            .build()
        news = LivePagedListBuilder(factory!!, config!!).setBoundaryCallback(callback!!).build()

    }


    suspend fun fetchAllNews(){
        viewModelScope.let {
            repository.fetchNews()
        }
    }


    fun deleteAllNews(){
        viewModelScope.let {
            repository.deleteAllNews()
        }
    }

    fun update(id:String){
        viewModelScope.let {
            repository.update(id)
        }
    }

}