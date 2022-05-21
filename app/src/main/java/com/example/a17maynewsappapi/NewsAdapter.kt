package com.example.a17maynewsappapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a17maynewsappapi.databinding.ActivityMainBinding
import com.example.a17maynewsappapi.databinding.ItemLayoutBinding
import com.google.android.material.R

class NewsAdapter(private var context: Context, val newsData: List<NewsData>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var binding: ItemLayoutBinding

    override fun getItemCount() = newsData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.apply {
            txtTitle.text = newsData[position].title
            txtDescription.text = newsData[position].description
            txtAuthor.text = newsData[position].author
            Glide.with(context).load(newsData[position].image)
                .placeholder(R.drawable.m3_popupmenu_background_overlay)
                .into(binding.imageView)


        }
    }

    inner class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = binding.txtTitle
        val txtAuthor: TextView = binding.txtAuthor
        val txtDescription: TextView = binding.txtDescription

    }

}