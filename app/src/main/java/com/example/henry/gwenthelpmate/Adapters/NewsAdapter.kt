package com.example.henry.gwenthelpmate.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.henry.gwenthelpmate.Containers.GwentCard
import com.example.henry.gwenthelpmate.Containers.News
import com.example.henry.gwenthelpmate.R
import com.squareup.picasso.Picasso


class NewsAdapter(private var list: ArrayList<News>, private var context: Context): RecyclerView.Adapter <NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder? {
        val view = LayoutInflater.from(context).inflate(R.layout.news_list_row,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindViews(list[position])
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var newsName = itemView.findViewById<TextView>(R.id.newsListName)
        var description = itemView.findViewById<TextView>(R.id.newsListDescription)
        var newsImage = itemView.findViewById<ImageView>(R.id.newsListImage)

        fun bindViews(newslist: News){
            newsName.text = newslist.newsTitle
            description.text = newslist.newsDescription
            Picasso.with(itemView.context).load(newslist.newsURL).into(newsImage)
        }
    }
}