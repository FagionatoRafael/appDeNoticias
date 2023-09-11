package com.newspost

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.newspost.databinding.ActivityMainBinding
import com.newspost.view.ReadNews
import com.newspost.view.WriteNews


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onCLickPublish()
        onCLickRead()
    }

    fun onCLickPublish() {
        binding.btnPostNews.setOnClickListener {
            navegateToPost(it)
        }
    }

    fun navegateToPost(view: View) {
        val intent = Intent(this, WriteNews::class.java)
        startActivity(intent)
    }


    fun onCLickRead() {
        binding.btnReadNews.setOnClickListener {
            navegateToRead(it)
        }
    }

    fun navegateToRead(view: View) {
        val intent = Intent(this, ReadNews::class.java)
        startActivity(intent)
    }
}