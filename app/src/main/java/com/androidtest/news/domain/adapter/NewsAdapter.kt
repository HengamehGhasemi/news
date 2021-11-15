package com.androidtest.news.domain.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.androidtest.news.R
import com.androidtest.news.network.models.News
import com.androidtest.news.ui.viewHolders.NewsViewHolder

class NewsAdapter() : PagedListAdapter<News, NewsViewHolder>(diffCallback) {

    private var onItemClickListener: OnItemClickListener? = null
    private var onLikeClickListener: OnLikeClickListener? = null
    private var onShareClickListener: OnShareClickListener? = null

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (getItem(position) != null && getItem(position)?.fields != null ) {
           holder.loadImage(getItem(position)?.fields?.thumbnail)
        }
        holder.newsDetail.setOnClickListener {
                onItemClickListener?.onItemClicked(getItem(position), position)
        }

        holder.like.setOnClickListener {
                holder.like.setImageResource(R.drawable.ic_baseline_favorite_24)
                onLikeClickListener?.onLikeClicked(getItem(position), position)

        }

        holder.share.setOnClickListener {
                onShareClickListener?.onShareClicked(getItem(position), position)
        }

        holder.title.text = getItem(position)?.webTitle

        holder.button.text = getItem(position)?.sectionName

        if (getItem(position)?.favorite != null && getItem(position)?.favorite == true)
            holder.like.setImageResource(R.drawable.ic_baseline_favorite_24)
        else
            holder.like.setImageResource(R.drawable.ic_baseline_favorite_border_24)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : NewsViewHolder {

        return NewsViewHolder(parent)
    }

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<News>() {

            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.id == newItem.id


            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(news: News?, position: Int)
    }

    interface OnLikeClickListener {
        fun onLikeClicked(news: News?, position: Int)
    }

    interface OnShareClickListener {
        fun onShareClicked(news: News?, position: Int)
    }


    fun setOnItemClickListener(itemClickListener : OnItemClickListener?) {
        onItemClickListener = itemClickListener
    }

    fun setOnLikeClickListener(itemClickListener : OnLikeClickListener?) {
        onLikeClickListener = itemClickListener
    }

    fun setOnShareClickListener(itemClickListener : OnShareClickListener?) {
        onShareClickListener = itemClickListener
    }
}