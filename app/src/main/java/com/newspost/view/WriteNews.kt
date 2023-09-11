package com.newspost.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.newspost.R
import com.newspost.databinding.ActivityWriteNewsBinding
import com.newspost.service.Api
import java.util.*

class WriteNews : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityWriteNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWriteNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        onCLickPublish()
        AddToDB()

    }

    fun AddToDB() {
        binding.btnPublish.setOnClickListener {
            verifyInputs()
        }
    }

    fun verifyInputs() {
        var titulo = binding.title.text.toString()
        var noticia = binding.writeNew.text.toString()
        var data = binding.publishDate.text.toString()
        var autor = binding.autor.text.toString()

        when {
            titulo.isEmpty() -> binding.title.error = "Preencha o titulo"
            noticia.isEmpty() -> binding.writeNew.error = "Escreva uma noticia"
            data.isEmpty() -> binding.publishDate.error = "Coloque uma data"
            autor.isEmpty() -> binding.autor.error = "Coloque um Autor"
            else -> {
                val api = Api()
                api.addPost(titulo, data, noticia, autor)
            }
        }
    }

    fun onCLickPublish() {
        binding.publishDate.setOnClickListener {
            createModal()
        }
    }

    fun createModal() {
        var c = Calendar.getInstance();
        var mYear = c.get(Calendar.YEAR);
        var mMonth = c.get(Calendar.MONTH);
        var mDay = c.get(Calendar.DAY_OF_MONTH);

        val datePickerDialog = DatePickerDialog(this,
            { view, year, monthOfYear, dayOfMonth ->
                binding.publishDate.setText(convertedDate(year, monthOfYear, dayOfMonth))
            },
            mYear,
            mMonth,
            mDay
        )
        datePickerDialog.show()
    }

    private fun convertedDate(year: Int, monthOfYear: Int, dayOfMonth: Int): String {
        return "${if(dayOfMonth <= 9 ) """0$dayOfMonth""" else dayOfMonth}/${if(monthOfYear + 1 <= 9 ) """0${monthOfYear + 1}""" else monthOfYear + 1}/${year}"
    }
}