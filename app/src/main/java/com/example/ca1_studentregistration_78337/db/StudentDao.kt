package com.example.ca1_studentregistration_78337.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ca1_studentregistration_78337.Student

// The DAO is the bridge between the database and the Student objects
@Dao
interface StudentDao {

    // Saves a new student and gives back the new id
    @Insert
    fun addStudent(student: Student): Long

    // Looks for one student by id and returns it
    @Query("SELECT * FROM Student WHERE id = :id")
    fun searchStudent(id: Int): Student?

    // Gets all the students, LiveData updates the screen by itself
    @Query("SELECT * FROM Student ORDER BY id ASC")
    fun getAllStudents(): LiveData<List<Student>>
}

