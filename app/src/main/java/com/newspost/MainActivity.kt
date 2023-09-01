package com.newspost

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.newspost.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        supportActionBar!!.hide()

        onCLickPublish()
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