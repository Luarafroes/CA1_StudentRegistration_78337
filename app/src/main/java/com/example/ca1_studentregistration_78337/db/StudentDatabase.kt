package com.example.ca1_studentregistration_78337.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ca1_studentregistration_78337.Student

// The database, it only has the Student table
@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    companion object {
        const val NAME = "Students_DB" // name of the database file
    }

    abstract fun getStudentDao(): StudentDao
}