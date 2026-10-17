package com.example.ca1_studentregistration_78337

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.ca1_studentregistration_78337.db.StudentDatabase

class MainApplication : Application() {

    companion object {
        lateinit var studentDatabase: StudentDatabase // our database object
    }

    override fun onCreate() {
        super.onCreate()
        // The database is created once when the app starts
        studentDatabase = databaseBuilder(
            applicationContext,
            StudentDatabase::class.java,
            StudentDatabase.NAME
        ).build()
    }
}