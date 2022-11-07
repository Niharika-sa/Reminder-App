package com.happiestminds.my_reminder_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout


class Show_Reminder : AppCompatActivity() {

    lateinit var adapter: ArrayAdapter<Reminder>
    lateinit var listViewReminder: ListView
    lateinit var reminderTextView: TextView
    lateinit var parentView: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_reminder)

        listViewReminder=findViewById(R.id.listT)
        reminderTextView=findViewById(R.id.remaindT)
        parentView=findViewById(R.id.parentL)

        adapter= ArrayAdapter<Reminder>(this,android.R.layout.simple_list_item_1,
            reminderList)

        listViewReminder.adapter= adapter
        listViewReminder.setOnItemClickListener{adapterView, view,index,l->
            val selectedReminder= reminderList[index]
            reminderList.removeAt(index)


            //reminderTextView.text="Selected Color: $selectedReminder"
            val dlg= ReminderDialog()
            dlg.isCancelable=false
            val dataBundle= Bundle()
            dataBundle.putString("msg","""Description: ${selectedReminder.description}
                |Date:${selectedReminder.date}
                |Time:${selectedReminder.time}
            """.trimMargin())

            dataBundle.putInt("idx",index)
            dataBundle.putString("id","${selectedReminder.title}")
            dataBundle.putString("descript","${selectedReminder.description}")
            dataBundle.putString("date","${selectedReminder.date}")
            dataBundle.putString("time","${selectedReminder.time}")

            dlg.arguments= dataBundle// passing argument to other class
            dlg.show(supportFragmentManager,null)


        }
    }

    override fun onResume() {
        setupData()
        val adapter= listViewReminder.adapter as ArrayAdapter<Reminder>
        adapter.notifyDataSetChanged()
        super.onResume()
    }
    private fun setupData() {
        val cursor = DBWrapper(this).getAllReminder()
        if (cursor.count > 0){
            val idx_title = cursor.getColumnIndexOrThrow(DBHelper.CLM_TITLE)
            val idx_description = cursor.getColumnIndexOrThrow(DBHelper.CLM_DESCRIPTION)
            val idx_date = cursor.getColumnIndexOrThrow(DBHelper.CLM_DATE)
            val idx_time = cursor.getColumnIndexOrThrow(DBHelper.CLM_TIME)
            cursor.moveToFirst()
            reminderList.clear()

            do{
                val title = cursor.getString(idx_title)
                val description = cursor.getString(idx_description)
                val date = cursor.getString(idx_date)
                val time = cursor.getString(idx_time)

                val rmd = Reminder(title, description, date, time)
                reminderList.add(rmd)
            }while (cursor.moveToNext())


            adapter.notifyDataSetChanged()

            Log.d("ListActivity", "List : $reminderList")
            Toast.makeText(this, "Found : ${reminderList.count()}",
                Toast.LENGTH_LONG).show()
        }
    }

    fun closeClick(view: View) {
        finish()
    }
}


