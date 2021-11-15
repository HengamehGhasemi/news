package com.androidtest.news.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidtest.news.R
import com.androidtest.news.domain.adapter.NewsAdapter
import com.androidtest.news.ui.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import com.androidtest.news.domain.util.Mapper
import com.androidtest.news.network.models.News
import com.androidtest.news.ui.viewModel.FavoriteViewModel
import com.androidtest.news.utils.checkTheInternetConnection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val viewModel: NewsViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val mapper = Mapper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       val adapter = NewsAdapter()
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter

        if (checkTheInternetConnection(context!!)) {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.fetchAllNews()
            }
        } else { // if the connection has problem or not available
            progresbar.visibility = View.GONE
            Toast.makeText(context,"The Internet is not connect",Toast.LENGTH_SHORT).show()
        }

        adapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClicked(news: News?, position: Int) {

                val action = NewsFragmentDirections.actionNewsFragmentToFullNewsFragment(
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

        adapter.setOnLikeClickListener(object : NewsAdapter.OnLikeClickListener {
            override fun onLikeClicked(news: News?, position: Int) {
                viewModel.update(news?.id!!)
                favoriteViewModel.insertFavorite(mapper.mapToFavoriteEntityModel(news))
            }
        })



        viewModel.news?.observe(viewLifecycleOwner) {
                if (it.size>0) {
                    progresbar.visibility = View.GONE
                    adapter.submitList(it)
                }
            }
        }
    }
