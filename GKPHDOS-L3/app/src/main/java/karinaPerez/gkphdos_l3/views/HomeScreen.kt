package karinaPerez.gkphdos_l3.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import karinaPerez.gkphdos_l3.data.Todo
import karinaPerez.gkphdos_l3.ui.theme.Blue
import karinaPerez.gkphdos_l3.ui.theme.DarkPurple
import karinaPerez.gkphdos_l3.views.components.Modal
import karinaPerez.gkphdos_l3.views.components.TodoCard
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {

    var todos = remember { mutableStateListOf<Todo>() }

    var openDialog = remember { mutableStateOf(false) }

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
    fun removeTodo(todoId: Int) {
        todos.removeIf { it.id == todoId }
    }

    fun changeDialogState() {
        openDialog.value = !openDialog.value
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blue)
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onBackClick() }, colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = White
                    )
                ) {
                    Text(text = "<", fontSize = 40.sp, color = Color.Black)
                }
                Spacer(Modifier.width(25.dp))
                Text("TODOS", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Button(
                onClick = {
                    changeDialogState()
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
            if (todos.isEmpty()) {
                item {
                    Text(text = "No tiene tareas pendientes...")
                }
            } else {
                items(todos) {
                    TodoCard(
                        todo = it,
                        onDelete = { id ->
                            removeTodo(id)
                        }
                    ) {
                        changeStatus(it.id)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        if (openDialog.value)
            Modal(
                onDismiss = { changeDialogState() },
                onConfirm = { title, description, color, priority, dateFor ->
                    addTodo(
                        title = title,
                        description = description,
                        color = color,
                        priority = priority,
                        dateFor = LocalDate.parse(dateFor)
                    )
                    changeDialogState()
                }
            )
    }
}