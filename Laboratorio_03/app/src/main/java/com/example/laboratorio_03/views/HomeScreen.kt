package com.example.laboratorio_03.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio_03.ui.theme.DarkPurple
import com.example.laboratorio_03.ui.theme.White
import com.example.laboratorio_03.viewmodels.TodoViewModel
import com.example.laboratorio_03.views.components.Modal
import com.example.laboratorio_03.views.components.TodoCard
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: TodoViewModel = viewModel<TodoViewModel>(),
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Button(
                onClick = {
                    viewModel.changeDialogState()
                },
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkPurple,
                    contentColor = White
                )
            ) {
                Text(
                    text = "+",
                    color = White,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (viewModel.todos.isEmpty()) {
                item {
                    Text(text = "No tiene tareas pendientes...")
                }
            } else {
                items(viewModel.todos) {
                    TodoCard(
                        todo = it,
                        onDelete = { id ->
                            viewModel.removeTodo(id)
                        }
                    ) {
                        viewModel.changeStatus(it.id)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        if (viewModel.openDialog)
            Modal(
                onDismiss = { viewModel.changeDialogState() },
                onConfirm = { title, description, color, priority, dateFor ->
                    viewModel.addTodo(
                        title = title,
                        description = description,
                        color = color,
                        priority = priority,
                        dateFor = LocalDate.parse(dateFor)
                    )
                    viewModel.changeDialogState()
                }
            )
    }

}

