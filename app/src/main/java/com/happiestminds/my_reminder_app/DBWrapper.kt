package com.happiestminds.my_reminder_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor


class DBWrapper(ctx: Context) {
    val helper = DBHelper(ctx)
    val db=helper.writableDatabase
    fun addRemainder(rmd:Reminder):Boolean{
        //insert
        val values = ContentValues()
        values.put(DBHelper.CLM_TITLE,rmd.title)
        values.put(DBHelper.CLM_DESCRIPTION,rmd.description)
        values.put(DBHelper.CLM_TIME, rmd.time)
        values.put(DBHelper.CLM_DATE, rmd.date)

        val rowid = db.insert(DBHelper.TABLE_NAME, null, values)
        if (rowid.toInt() == -1){
            return false
        }
        return true
    }
    fun getAllReminder(): Cursor {
        //query
        val clms = arrayOf(DBHelper.CLM_TITLE, DBHelper.CLM_DESCRIPTION, DBHelper.CLM_TIME, DBHelper.CLM_DATE)

        return db.query(DBHelper.TABLE_NAME, clms,
            null, null, null, null, null)
    }

    fun deleteReminder(rmd: Reminder){
        //delete
        db.delete(DBHelper.TABLE_NAME, "${DBHelper.CLM_TITLE} = ?",
            arrayOf(rmd.title.toString()))
    }
}
data class Remainder(val title: String, val description: String, val time: String, val date: String){
    override  fun toString(): String {
        return """
            Title: $title
            Description: $description
            Time: $time
            Date: $date
        """.trimIndent()
    }
}