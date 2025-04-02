package com.example.laboratorio_03.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.laboratorio_03.data.Todo
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class TodoViewModel : ViewModel() {
    var todos = mutableStateListOf<Todo>()

    var openDialog by mutableStateOf(false)

    fun addTodo(
        title: String,
        description: String,
        color: Color,
        priority: Int,
        dateFor: LocalDate
    ) {
        val newTodo = Todo(
            id = todos.size + 1,
            title = title,
            description = description,
            color = color,
            priority = priority,
            timestamp = LocalDate.now(),
            forDate = dateFor,
            isDone = false
        )
        println(newTodo)
        todos.add(newTodo)
        println(todos)
    }

    fun changeStatus(todoId: Int) {
        val index = todos.indexOfFirst { it.id == todoId }
        if (index != -1) {
            todos[index] = todos[index].copy(isDone = !todos[index].isDone)
        }
    }

    fun changeDialogState() {
        openDialog = !openDialog
    }

}