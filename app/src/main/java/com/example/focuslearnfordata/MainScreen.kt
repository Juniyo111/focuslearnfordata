package com.example.focuslearnfordata

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.focuslearnfordata.MenuItem

@Composable
fun MainScreen(navController: NavHostController) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header Row
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { isMenuExpanded = !isMenuExpanded }
            )
            if (isMenuExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth() // Ensure the card takes full width
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .shadow(4.dp, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .padding(top = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        MenuItem(text = "직원 아이디 생성", navController = navController, destination = "newEmployeeScreen")
                        MenuItem(text = "직원 교육 현황 관리", navController = navController, destination = "employeeTrainingStatusScreen")
                        MenuItem(text = "교육 이수 통계 및 보고서", navController = navController, destination = "employeeEduStatisticsScreen")
                        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
                        MenuItem(text = "공지사항 / 알림", navController = navController, destination = "notificationScreen")
                    }
                }
            }
        }

        // Spacer to push the card slightly to the left
        Spacer(modifier = Modifier.height(16.dp))

        // Main Content Card
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f) // Adjust the width as needed
                .background(Color.White, RoundedCornerShape(8.dp))
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.Start),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Title Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically // Ensure the text and menu icon are vertically aligned
                ) {
                    Text(
                        text = "FOCUS LEARN",
                        color = Color.Blue,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.Black,
                        modifier = Modifier.clickable { isMenuExpanded = !isMenuExpanded }
                    )
                }
                // Add "목록" below "FOCUS LEARN"
                Text(
                    text = "목록",
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // Make "목록" bold
                    modifier = Modifier.padding(top = 8.dp, start = 0.dp) // Align with FOCUS LEARN
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}



