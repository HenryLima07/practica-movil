package karinaPerez.gkphdos_l3.views.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import karinaPerez.gkphdos_l3.ui.theme.Pink
import karinaPerez.gkphdos_l3.ui.theme.Purple
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Modal(
    onDismiss: () -> Unit,
    onConfirm: (title: String, description: String, color: Color, priority: Int, dateFor: String) -> Unit,
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(1) }
    var color by remember { mutableStateOf(Color.Red) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""


    AlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Titulo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(1.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Descripcion") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(3.dp))

            OutlinedTextField(
                value = priority.toString(),
                onValueChange = {
                    val parsed = it.toIntOrNull()
                    if (parsed != null) priority = parsed
                },
                label = { Text("Prioridad numero") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Selecciona un color:")
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                val colors = listOf(Blue, Purple, Pink)
                colors.forEach {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(it, shape = RoundedCornerShape(8.dp))
                            .border(2.dp, if (it == color) Color.Black else Color.Transparent)
                            .clickable { color = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Seleccionar fecha")
            DatePickerDocked(datePickerState = datePickerState, selectedDate = selectedDate)
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(text = "Cancelar")
                }
                Button(onClick = {
                    onConfirm(
                        title,
                        description,
                        color,
                        priority,
                        selectedDate
                    )
                }) {
                    Text(text = "Confirmar")
                }
            }
        }
    }
}

//https://developer.android.com/develop/ui/compose/components/datepickers
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    datePickerState: DatePickerState = rememberDatePickerState(),
    selectedDate: String = ""
) {
    var showDatePicker by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text(text = "DOB") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                        .size(width = 300.dp, height = 400.dp)
                ) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = {
                                showDatePicker = false
                            }) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDatePicker = false }) {
                                Text("Cancel")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToDate(millis: Long): String {
    val date = Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    return date.toString()
}