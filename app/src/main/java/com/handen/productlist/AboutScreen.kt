package com.handen.productlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(56.dp),
                backgroundColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "About",
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Группа",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
            Text(
                text = "951008",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Фамилия",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
            Text(
                text = "Плетинский",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Номер лабораторной работы",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
            Text(
                text = "2",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}