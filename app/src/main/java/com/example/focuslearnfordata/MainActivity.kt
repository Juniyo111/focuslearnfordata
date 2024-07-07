package com.example.focuslearnfordata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.focuslearnfordata.LogIn
import com.example.focuslearnfordata.ui.theme.FocuslearnfordataTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FocuslearnfordataTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") { LogIn(navController) }
        composable("signUp") { SignUpScreen(navController) }
        composable("mainScreen") { MainScreen(navController) }
        composable("newEmployeeScreen") { NewEmployeeScreen(navController) }
        composable("employeeTrainingStatusScreen") { EmployeeTrainingStatusScreen(navController) }
        composable("employeeEduStatisticsScreen") { EmployeeEduStatisticsScreen(navController) }
        composable("notificationScreen") { NotificationScreen(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    FocuslearnfordataTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyApp()
        }
    }
}
