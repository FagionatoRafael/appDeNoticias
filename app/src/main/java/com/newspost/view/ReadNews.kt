package com.newspost.view

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.newspost.R
import com.newspost.adapter.AdapterPost
import com.newspost.databinding.ActivityReadNewsBinding
import com.newspost.model.Post
import com.newspost.service.Api

class ReadNews : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityReadNewsBinding
    lateinit var adapterPost: AdapterPost
    private val listaPost: MutableList<Post> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReadNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        InitAdaptorPost()
    }

    fun InitAdaptorPost() {
        val listPost = binding.recyclerList
        listPost.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listPost.setHasFixedSize(true)

        val api = Api()
        api.getPost()

        api.responseData.observe(this, Observer {
            for (post in it) {
                listaPost.add(post)
            }
            adapterPost = AdapterPost(this, listaPost)
            listPost.adapter = adapterPost
        })

    }

    fun listPosts() {
        val api = Api()
        api.getPost()
        var list = listOf<Post>()
        api.responseData.observe(this, Observer {
            list = it
        })
        for (post in list) {
            listaPost.add(post)
        }
    }
}