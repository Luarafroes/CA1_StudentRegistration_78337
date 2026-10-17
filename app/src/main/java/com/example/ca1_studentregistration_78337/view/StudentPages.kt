package com.example.ca1_studentregistration_78337.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ca1_studentregistration_78337.Student

// ---------- Home page ----------
@Composable
fun HomePage(onRegister: () -> Unit, onSearch: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Student Registration", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRegister, modifier = Modifier.fillMaxWidth()) {
            Text("Register Student")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onSearch, modifier = Modifier.fillMaxWidth()) {
            Text("Search Student")
        }
    }
}

// ---------- Registration page: the form to add a student ----------
@Composable
fun RegisterPage(viewModel: StudentViewModel, onBack: () -> Unit) {

    // What the user is typing in each box
    var name by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    // The message from the ViewModel (success or error)
    val message by viewModel.registerMessage.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Register Student", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = course,
            onValueChange = { course = it },
            label = { Text("Course") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = year,
            onValueChange = { year = it },
            label = { Text("Year (1-4)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // The button sends the data to the ViewModel
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.addStudent(name, course, year) }
        ) {
            Text("Register")
        }

        // Show the message only if there is one
        if (message.isNotEmpty()) {
            Text(message)
        }

        TextButton(onClick = onBack) { Text("Back to Home") }
    }
}

// ---------- Search page: search by id with the button and filter the list by name or id ----------
@Composable
fun SearchPage(viewModel: StudentViewModel, onBack: () -> Unit) {

    // What the user is typing in the search box
    var searchText by remember { mutableStateOf("") }

    // The things this page is watching (LiveData)
    val student by viewModel.foundStudent.observeAsState(null)
    val message by viewModel.searchMessage.observeAsState("")
    val students by viewModel.studentList.observeAsState(emptyList())

    // I filter the list: the name contains the text OR the id is the same as the text
    // (if the box is empty, contains("") is true so all the students are shown)
    val filteredStudents = students.filter {
        it.name.contains(searchText.trim(), ignoreCase = true) ||
                it.id.toString() == searchText.trim()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Search Student", style = MaterialTheme.typography.headlineSmall)

        // One box for both: typing filters the list, the button searches by id
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Student ID or name") }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.searchStudent(searchText) }
        ) {
            Text("Search by ID")
        }

        // If the student was found it show the name and the course
        student?.let {
            Text("Name: ${it.name}")
            Text("Course: ${it.course}")
        }

        // If the student was not found it show the error message
        if (message.isNotEmpty()) {
            Text(message, color = MaterialTheme.colorScheme.error)
        }

        // The list of students (filtered by what I typed)
        Text("Students", style = MaterialTheme.typography.titleMedium)

        if (filteredStudents.isEmpty()) {
            Text("No students found.")
        } else {
            // LazyColumn is a list that can scroll
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredStudents) { item ->
                    StudentItem(item)
                }
            }
        }

        TextButton(onClick = onBack) { Text("Back to Home") }
    }
}

// One card for each student in the list
@Composable
fun StudentItem(student: Student) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ID: ${student.id}")
            Text(student.name, style = MaterialTheme.typography.titleMedium)
            Text("Course: ${student.course}")
            Text("Year: ${student.year}")
        }
    }
}