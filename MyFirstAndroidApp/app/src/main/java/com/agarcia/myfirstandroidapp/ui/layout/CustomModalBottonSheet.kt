package com.agarcia.myfirstandroidapp.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalBottonSheet() {
  val sheetState = rememberModalBottomSheetState()
  var isVisible by rememberSaveable { mutableStateOf(false) }

  Box() {
    Button(onClick = { isVisible = true }) {
      Text("Show Modal")
    }
  }

  if (isVisible) {
    ModalBottomSheet(
      sheetState = sheetState,
      onDismissRequest = {
        isVisible = false
      },
    ) {
      Column {
        Text("Hello World")
        Text("Hello World")
        Text("Hello World")
        Text("Hello World")
        Text("Hello World")
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CustomModalBottonSheetPreview() {
  CustomModalBottonSheet()
}