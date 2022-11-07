package com.happiestminds.my_reminder_app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Exit")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    fun reminderClick(view: View) {
        val reminderIntent = Intent(this,Add_Reminder().javaClass)
        startActivity(reminderIntent)
    }

    fun displayClick(view: View) {
        val showIntent=Intent(this,Show_Reminder().javaClass)
        startActivity(showIntent)
    }

    override fun onResume() {
        if (checkSelfPermission(Manifest.permission.READ_CALENDAR)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR),1)
        }
        super.onResume()
    }
}