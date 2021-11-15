package com.androidtest.news.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidtest.news.R
import com.androidtest.news.domain.adapter.NewsAdapter
import com.androidtest.news.network.models.News
import com.androidtest.news.ui.viewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsAdapter()
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fetchAllFavorite()
        }

        viewModel.news?.observe(viewLifecycleOwner) {
            if (it.size>0)
            adapter.submitList(it)
        }

        adapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClicked(news: News?, position: Int) {

                val action = FavoritesFragmentDirections.actionFavoritesFragmentToFullNewsFragment(
                    news?.fields?.thumbnail!!,
                    news.webUrl,
                    news.webTitle)
                findNavController().navigate(action)

            }

        })

        adapter.setOnShareClickListener(object : NewsAdapter.OnShareClickListener {
            override fun onShareClicked(news: News?, position: Int) {

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, news?.webUrl)
                    type = "text/plain"
                }
                startActivity(sendIntent)

            }

        })
    }

}