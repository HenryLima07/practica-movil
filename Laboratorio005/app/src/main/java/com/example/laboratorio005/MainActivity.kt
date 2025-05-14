package com.example.laboratorio005

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio005.ui.theme.Laboratorio005Theme
import com.example.laboratorio005.views.BooksView
import com.example.laboratorio005.views.BooksViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio005Theme {
                val viewModel: BooksViewModel = viewModel()
                BooksView(viewModel = viewModel, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
