package com.example.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_history.setNavigationOnClickListener { onBackPressed() }
    }
}