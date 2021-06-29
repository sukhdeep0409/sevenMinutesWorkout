package com.example.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_history.setNavigationOnClickListener { onBackPressed() }

        getAllCompletedDates()
    }

    private fun getAllCompletedDates() {
        val dbHandler = SQLiteDatabaseHelper(this, null)
        val allCompletedDateList: ArrayList<String> = dbHandler.getAllCompleteDateList()

        if (allCompletedDateList.size > 0) {
            layout_history.visibility = View.VISIBLE
            no_history.visibility = View.GONE

            recycler_view_history.layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryAdapter(this, allCompletedDateList)
            recycler_view_history.adapter = historyAdapter
        }
        else {
            layout_history.visibility = View.GONE
            no_history.visibility = View.VISIBLE
        }
    }
}