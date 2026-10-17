package com.example.ca1_studentregistration_78337

import androidx.room.Entity
import androidx.room.PrimaryKey

// This is the table for the database
@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) // the id is created automatically
    var id: Int = 0,
    var name: String,
    var course: String,
    var year: Int
)