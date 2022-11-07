package com.happiestminds.my_reminder_app

var reminderList= mutableListOf<Reminder>()
data class Reminder(var title: String, var description:String, var date:String, var time:String){
    override fun toString(): String {
        return "$title\n$description\n$date\n$time\n"
    }
}