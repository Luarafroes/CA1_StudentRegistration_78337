package com.example.ca1_studentregistration_78337

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ca1_studentregistration_78337.ui.theme.CA1_StudentRegistration_78337Theme
import com.example.ca1_studentregistration_78337.view.HomePage
import com.example.ca1_studentregistration_78337.view.RegisterPage
import com.example.ca1_studentregistration_78337.view.SearchPage
import com.example.ca1_studentregistration_78337.view.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // One ViewModel shared by all the pages
        val studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        setContent {
            CA1_StudentRegistration_78337Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // The navController changes between the pages
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomePage(
                                onRegister = { navController.navigate("register") },
                                onSearch = { navController.navigate("search") }
                            )
                        }
                        composable("register") {
                            RegisterPage(studentViewModel) {
                                studentViewModel.clearMessages()
                                navController.popBackStack()
                            }
                        }
                        composable("search") {
                            SearchPage(studentViewModel) {
                                studentViewModel.clearMessages()
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}