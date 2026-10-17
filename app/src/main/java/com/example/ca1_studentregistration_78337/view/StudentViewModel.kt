package com.example.ca1_studentregistration_78337.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca1_studentregistration_78337.MainApplication
import com.example.ca1_studentregistration_78337.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {

    val studentDao = MainApplication.studentDatabase.getStudentDao()

    // The list of all the students
    val studentList: LiveData<List<Student>> = studentDao.getAllStudents()

    // Private so only the ViewModel can change them
    private val _registerMessage = MutableLiveData("")
    private val _foundStudent = MutableLiveData<Student?>(null)
    private val _searchMessage = MutableLiveData("")

    // Public so the pages can only read them
    val registerMessage: LiveData<String> = _registerMessage
    val foundStudent: LiveData<Student?> = _foundStudent
    val searchMessage: LiveData<String> = _searchMessage

    fun addStudent(name: String, course: String, yearText: String) {
        val year = yearText.toIntOrNull()

        // Validation: it check everything before saving in the database
        if (name.isBlank() || course.isBlank()) {
            _registerMessage.value = "Please fill in all fields."
        } else if (year == null || year !in 1..4) {
            // The year can only be 1, 2, 3 or 4
            _registerMessage.value = "Year must be a number between 1 and 4."
        } else {
            // Everything is correct so it save it in the background
            viewModelScope.launch(Dispatchers.IO) {
                val newId = studentDao.addStudent(
                    Student(name = name.trim(), course = course.trim(), year = year)
                )
                _registerMessage.postValue("Registered! Student ID: $newId")
            }
        }
    }

    fun searchStudent(idText: String) {
        val id = idText.toIntOrNull()

        // If the id is not a number it show an error
        if (id == null) {
            _foundStudent.value = null
            _searchMessage.value = "Enter a numeric Student ID or name"
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val student = studentDao.searchStudent(id)
            _foundStudent.postValue(student)
            // If the student is null it means it is not in the database
            _searchMessage.postValue(
                if (student == null) "No student found with ID $id." else ""
            )
        }
    }

    // Clears the messages when I leave a page
    fun clearMessages() {
        _registerMessage.value = ""
        _searchMessage.value = ""
        _foundStudent.value = null
    }
}