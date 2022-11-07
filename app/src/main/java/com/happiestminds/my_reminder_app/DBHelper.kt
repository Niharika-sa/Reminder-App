package com.happiestminds.my_reminder_app

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context) : SQLiteOpenHelper(context,"reminder.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //table creation to be done (executed when db not present)
        db?.execSQL(TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //executed when version mismatch
        //drop table,create new table,modify schema of existing version
    }

    companion object {
        const val TABLE_NAME= "ReminderData"
        const val CLM_TITLE = "Title"
        const val CLM_DESCRIPTION = "Description"
        const val CLM_TIME = "Time"
        const val CLM_DATE = "Date"
        private const val TABLE_QUERY =
            "create table $TABLE_NAME($CLM_TITLE text PRIMARY KEY,$CLM_DESCRIPTION text ," +
                    "$CLM_TIME text,$CLM_DATE text)"
    }
}