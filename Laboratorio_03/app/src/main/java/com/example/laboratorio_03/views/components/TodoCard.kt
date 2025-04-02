package com.example.laboratorio_03.views.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio_03.data.Todo
import com.example.laboratorio_03.data.dateToString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoCard(
    todo: Todo,
    modifier: Modifier = Modifier,
    onClick: (Todo) -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
            .background(
                if (todo.isDone) Color.LightGray else todo.color, RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = todo.title)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = dateToString(todo.forDate), fontSize = 14.sp)
            Checkbox(checked = todo.isDone, onCheckedChange = {
                onClick(todo)
            })
        }
    }
}