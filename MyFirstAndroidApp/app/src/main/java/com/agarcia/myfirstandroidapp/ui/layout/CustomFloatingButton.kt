package com.agarcia.myfirstandroidapp.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomFloatingButton(
  text: String = "Add",
  onClick: () -> Unit = {},
  icon: ImageVector = Icons.Filled.Add,
) {
  ExtendedFloatingActionButton(
    text = { Text(text) },
    icon = { Icon(imageVector = icon, contentDescription = text) },
    onClick = { onClick() },
//    containerColor = Color.Green,
//    contentColor = Color.Black,
  )
}
