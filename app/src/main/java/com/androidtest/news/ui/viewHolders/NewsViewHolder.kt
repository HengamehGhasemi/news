package com.androidtest.news.ui.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.androidtest.news.R
import com.androidtest.news.domain.util.imageLoader

class NewsViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder (
    LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)){

    private var image = itemView.findViewById<ImageView>(R.id.news_image_view)
    var title = itemView.findViewById<TextView>(R.id.title_text_view)
    var newsDetail = itemView.findViewById<ConstraintLayout>(R.id.new_detail_constraint_layout)
    var like = itemView.findViewById<ImageView>(R.id.favorite_image_view)
    var share = itemView.findViewById<ImageView>(R.id.share_image_view)
    var button = itemView.findViewById<Button>(R.id.section)
    val context = parent.context

    fun loadImage(url:String?){
        imageLoader(context,url,image)
    }
}