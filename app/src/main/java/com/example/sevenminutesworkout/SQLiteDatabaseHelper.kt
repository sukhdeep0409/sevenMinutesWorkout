package com.example.sevenminutesworkout

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteDatabaseHelper
constructor(context: Context, factory: SQLiteDatabase.CursorFactory?):
SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "SevenMinutesWorkOut.db"
        private const val TABLE_HISTORY = "WORKOUT_HISTORY"
        private const val COL_ID = "_ID"
        private const val COL_COMPLETED_DATE = "Completed_Date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createExerciseTable = "CREATE TABLE $TABLE_HISTORY ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_COMPLETED_DATE TEXT)"
        db?.execSQL(createExerciseTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        onCreate(db)
    }

    fun addDate(date: String) {
        val values = ContentValues()
        values.put(COL_COMPLETED_DATE, date)

        val database = this.writableDatabase
        database.insert(TABLE_HISTORY, null, values)

        database.close()
    }

    fun getAllCompleteDateList(): ArrayList<String> {

        val list = ArrayList<String>()
        val database = this.readableDatabase

        val cursor: Cursor = database.rawQuery("SELECT * FROM $TABLE_HISTORY", null)
        while (cursor.moveToNext()) {
            val dateValue = cursor.getString(cursor.getColumnIndex(COL_COMPLETED_DATE))
            list.add(dateValue)
        }

        cursor.close()
        return list
    }
}