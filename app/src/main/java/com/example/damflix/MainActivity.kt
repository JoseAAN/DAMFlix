package com.example.damflix

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.damflix.data.PeliculaRepository
import com.example.damflix.data.local.AppDatabase
import com.example.damflix.navigation.AppDAMFlixMainScreen
import com.example.damflix.ui.theme.DAMFlixTheme
import com.example.damflix.viewmodel.PeliculaViewModel
import com.example.damflix.viewmodel.PeliculaViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as DAMFlixApplication

        val factory = PeliculaViewModelFactory(app.repository)
        setContent {
            DAMFlixTheme {
                Scaffold {
                    val viewModel: PeliculaViewModel = viewModel(factory = factory)

                    // 4. Invocación del contenedor principal de la UI (Drawer + Navigation)
                    AppDAMFlixMainScreen(viewModel)
                }
            }
        }
    }
}
