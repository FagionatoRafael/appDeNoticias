package com.newspost.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newspost.databinding.PostBinding
import com.newspost.model.Post

class AdapterPost(private val context: Context, private val listPost: MutableList<Post>)
    : RecyclerView.Adapter<AdapterPost.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemLista = PostBinding.inflate(LayoutInflater.from(context), parent, false)
        return PostViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = listPost[position].title
        holder.news.text = listPost[position].news
        holder.date.text = listPost[position].publishDate
        holder.autor.text = listPost[position].autor
    }

    override fun getItemCount() = listPost.size

    inner class PostViewHolder(binding: PostBinding): RecyclerView.ViewHolder(binding.root) {
        val title = binding.txtTitle
        val news = binding.txtNews
        val date = binding.txtDate
        val autor = binding.txtAutor
    }
}