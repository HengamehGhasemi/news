package com.androidtest.news.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidtest.news.cache.dao.FavoritesNewsDao
import com.androidtest.news.cache.model.FavoritEntity
import com.androidtest.news.domain.callBack.FavoriteNewsBoundaryCallback
import com.androidtest.news.domain.util.Mapper
import com.androidtest.news.network.models.News
import com.androidtest.news.network.repository.GetFavoriteNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor (
    private val repository: GetFavoriteNewsRepository,
    private val favoritesNewsDao: FavoritesNewsDao
) : ViewModel() {

    val mapper = Mapper()
    var callback: FavoriteNewsBoundaryCallback? ? = null
    var factory: DataSource.Factory<Int, News> ? = null
    var config : PagedList.Config ? = null
    var news: LiveData<PagedList<News>> ? = null

    init {
        callback = FavoriteNewsBoundaryCallback(repository)
        factory  = mapper.toFavoriteNewsDataSourceList(favoritesNewsDao?.getFavoritesNews())
        config = PagedList.Config.Builder()
            .setPageSize(60)
            .setInitialLoadSizeHint(60)
            .setEnablePlaceholders(true)
            .build()
        news = LivePagedListBuilder(factory!!, config!!).setBoundaryCallback(callback!!).build()

    }


     fun fetchAllFavorite(){
        viewModelScope.let {
            repository.getAllNews()
        }
    }

    fun insertFavorite(newsEntity: FavoritEntity){
        viewModelScope.let {
            repository.insertNews(newsEntity)
        }
    }

}