package com.agarcia.myfirstandroidapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.agarcia.myfirstandroidapp.data.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieBottomSheet(
    movie: Movie,
    sheetState: SheetState = rememberModalBottomSheetState(),
    reviewText: String,
    onDismissRequest: () -> Unit,
    onSaveReview: (Movie, String) -> Unit,
    onReviewChange: (String) -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                text = "Escribe tu reseña para ${movie.title}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campo de texto para la reseña
            OutlinedTextField(
                value = reviewText,
                onValueChange = { onReviewChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp),
                label = { Text("Tu reseña") },
                placeholder = { Text("Escribe lo que piensas sobre esta película...") },
                maxLines = 5,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Aquí puedes manejar la acción al presionar Done
                    }
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para guardar
            Button(
                onClick = {
                    onSaveReview(movie, reviewText)
                    onDismissRequest()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = reviewText.isNotBlank()
            ) {
                Text(text = "Guardar reseña")
            }
        }
    }
}
