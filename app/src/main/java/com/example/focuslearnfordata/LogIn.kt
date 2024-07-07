package com.example.focuslearnfordata

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.focuslearnfordata.ui.theme.FocuslearnfordataTheme

@Composable
fun LogIn(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        //.padding(70.dp, bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Text(
            text = "FOCUS LEARN",
            color = Color.Blue,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 3.dp, end = 3.dp, bottom = 16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "법정 의무 교육 플랫폼에 오신 것을 환영합니다",
            color = Color.Black,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 90.dp),
            textAlign = TextAlign.Center
        )
        TextInputField(
            label = "ID 입력",
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                .background(Color(0xFFEBF0FF)) // Set background color here as well
                .clip(RoundedCornerShape(8.dp)) // Clip to match the background shape
        )
        TextInputField(
            label = "pw 입력",
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                .background(Color(0xFFEBF0FF)) // Set background color here as well
                .clip(RoundedCornerShape(8.dp)) // Clip to match the background shape
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "회원가입",
                color = Color.Blue,
                modifier = Modifier
                    .clickable { navController.navigate("signUp") }
                    .padding(top = 16.dp),
                textAlign = TextAlign.Right,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { navController.navigate("mainScreen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "로그인", color = Color.White)
        }
        Spacer(modifier = Modifier.height(240.dp)) // Adding space between the button and "C Intel"
        Text(
            text = "C Intel",
            color = Color.Blue,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(start = 3.dp, end = 3.dp, bottom = 10.dp), // Adjusted padding
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    FocuslearnfordataTheme {
        val navController = rememberNavController()
        LogIn(navController)
    }
}

