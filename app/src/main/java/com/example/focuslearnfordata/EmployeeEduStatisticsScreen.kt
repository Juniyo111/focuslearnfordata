package com.example.focuslearnfordata

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun EmployeeEduStatisticsScreen(navController: NavHostController) {
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

        // Centered Title
        Text(
            text = "임직원 교육 현황",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Ensure the text takes up the full width
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtitle
        Text(
            text = "7월 최근 한 달간의 교육 이수 상태 변화",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "전체 교육 이수율(%)",
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(1.dp))

        // Real Line Chart
        LineChartView()

        Spacer(modifier = Modifier.height(40.dp))

        // Missing employees list
        Text(
            text = "미이수자 목록",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        // List Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "이름", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(text = "부서", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(text = "고용 형태", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(text = "미이수 교육 목록", fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.2f), textAlign = TextAlign.Center)
            Text(text = "미이수 사유", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f), textAlign = TextAlign.Center)
        }

        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

        // Dummy Data for Missing Employees
        val missingEmployees = listOf(
            TrainingEmployee("1001", "김철수", "인사부", "사원", "개인정보보호법", "미완료"),
            TrainingEmployee("1002", "이영희", "영업부", "대리", "산업안전법", "미완료")
        )

        // Real Data for Missing Employees
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(missingEmployees) { employee ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 1.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TableCell(text = employee.name, modifier = Modifier.weight(0.7f))
                    TableCell(text = employee.department, modifier = Modifier.weight(0.6f))
                    TableCell(text = employee.position, modifier = Modifier.weight(0.5f))
                    TableCell(text = employee.course, modifier = Modifier.weight(1.5f))
                    TableCell(text = employee.status, modifier = Modifier.weight(0.7f))
                }
                Divider(color = Color.Gray, thickness = 0.5.dp)
            }
        }
    }
}

@Composable
fun TableCell(text: String, modifier: Modifier = Modifier, fontWeight: FontWeight? = null) {
    Text(
        text = text,
        fontWeight = fontWeight ?: FontWeight.Normal,
        modifier = modifier
            .padding(8.dp),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun EmployeeEduStatisticsScreenPreview() {
    val navController = rememberNavController()
    EmployeeEduStatisticsScreen(navController)
}
