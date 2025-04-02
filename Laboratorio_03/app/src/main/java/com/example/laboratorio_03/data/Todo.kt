package com.example.laboratorio_03.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import java.time.LocalDate

data class Todo(
    val id: Int,
    val title: String,
    val priority: Int,
    val color: Color,
    val timestamp: LocalDate,
    val forDate: LocalDate,
    val isDone: Boolean
)

@RequiresApi(Build.VERSION_CODES.O)
fun dateToString(date: LocalDate): String {
    return "${date.dayOfMonth}/${date.monthValue}/${date.year}";
}