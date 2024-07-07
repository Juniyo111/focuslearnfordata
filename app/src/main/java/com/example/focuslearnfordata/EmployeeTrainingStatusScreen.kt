package com.example.focuslearnfordata

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.focuslearnfordata.MenuItem


data class TrainingEmployee(
    val id: String,
    val name: String,
    val department: String,
    val position: String,
    val course: String,
    val status: String
)

@Composable
fun EmployeeTrainingStatusScreen(navController: NavHostController) {
    val employees = listOf(
        TrainingEmployee("1001", "김철수", "인사부", "사원", "개인정보보호법", "완료"),
        TrainingEmployee("1002", "이영희", "영업부", "대리", "산업안전법", "진행 중")
    )

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var isMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header Row with Icons
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = Color.Black,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { isMenuExpanded = !isMenuExpanded }
            )

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (isMenuExpanded) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .shadow(4.dp, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    MenuItem(
                        text = "직원 아이디 생성",
                        navController = navController,
                        destination = "newEmployeeScreen"
                    )
                    MenuItem(
                        text = "직원 교육 현황 관리",
                        navController = navController,
                        destination = "employeeTrainingStatusScreen"
                    )
                    MenuItem(
                        text = "교육 이수 통계 및 보고서",
                        navController = navController,
                        destination = "employeeEduStatisticsScreen"
                    )
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    MenuItem(
                        text = "공지사항 / 알림",
                        navController = navController,
                        destination = "notificationScreen"
                    )
                }
            }
        }

        Text(
            text = "직원 교육 현황 리스트",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "검색", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("이름") },
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            )
            FilterButton()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Employee List Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "직원 ID", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.6f), textAlign = TextAlign.Center)
            Text(text = "이름", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
            Text(text = "부서", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.6f), textAlign = TextAlign.Center)
            Text(text = "직급", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.6f), textAlign = TextAlign.Center)
            Text(text = "교육과정명", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f), textAlign = TextAlign.Center)
            Text(text = "진행상태", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.6f), textAlign = TextAlign.Center)
        }

        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

        // Employee List
        employees.forEach { employee ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = employee.id, modifier = Modifier.weight(0.7f), textAlign = TextAlign.Center)
                Text(text = employee.name, modifier = Modifier.weight(0.7f), textAlign = TextAlign.Center)
                Text(text = employee.department, modifier = Modifier.weight(0.7f), textAlign = TextAlign.Center)
                Text(text = employee.position, modifier = Modifier.weight(0.7f), textAlign = TextAlign.Center)
                Text(text = employee.course, modifier = Modifier.weight(1.6f), textAlign = TextAlign.Center)
                Text(
                    text = employee.status,
                    modifier = Modifier.weight(0.7f), textAlign = TextAlign.Center,
                    color = if (employee.status == "완료") Color.Red else Color.Blue
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(text = "총 직원 수 : 53 , 필터링 된 직원 수 : 20",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        // Pagination
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Pagination()
        }
    }
}

@Composable
fun FilterButton() {
    Button(
        onClick = { /* Handle filter action */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
    ) {
        Text("필터", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown Arrow"
        )
    }
}

@Composable
fun Pagination() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextButton(onClick = { /* Handle previous page */ }) {
            Text("1")
        }
        TextButton(onClick = { /* Handle next page */ }) {
            Text("2")
        }
        TextButton(onClick = { /* Handle next page */ }) {
            Text("3")
        }
        Text("...")
        TextButton(onClick = { /* Handle next page */ }) {
            Text("6")
        }
        TextButton(onClick = { /* Handle next page */ }) {
            Text("7")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeTrainingStatusScreenPreview() {
    val navController = rememberNavController()
    EmployeeTrainingStatusScreen(navController)
}


