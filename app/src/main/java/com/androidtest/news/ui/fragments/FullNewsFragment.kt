package com.androidtest.news.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.androidtest.news.R
import com.androidtest.news.domain.util.imageLoader
import com.androidtest.news.ui.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.android.synthetic.main.fragment_full_news.*
import android.content.Intent


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FullNewsFragment : Fragment(R.layout.fragment_full_news) {

    private val viewModel: NewsViewModel by viewModels()
    private val args: FullNewsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoader(context!!,args.imageUrl,news_image_view)
        title_text_view.text = args.title

        share_image_view.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, args.webUrl)
                type = "text/plain"
            }
            startActivity(sendIntent)
        }
    }
}