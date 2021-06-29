package com.example.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        finish_exercise.setOnClickListener {
            finish()
            onBackPressed()
        }

        addDateToDatabase()
    }

    private fun addDateToDatabase() {
        val calendar: Calendar = Calendar.getInstance()
        val dateTime = calendar.time
        Log.i("DATE: ", dateTime.toString())

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        val dbHandler = SQLiteDatabaseHelper(this, null)
        dbHandler.addDate(date)
        Log.i("DATE: ", "Added")
    }
}