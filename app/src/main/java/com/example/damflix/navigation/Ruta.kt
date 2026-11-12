package com.example.damflix.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.damflix.ui.screens.FormularioPelicula
import com.example.damflix.ui.screens.ListaPeliculasScreen
import com.example.damflix.ui.screens.PerfilUsuario
import com.example.damflix.viewmodel.PeliculaViewModel
import kotlinx.coroutines.launch

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.damflix.DAMFlixApplication
import com.example.damflix.ui.screens.PantallaDetalleScreen
import com.example.damflix.viewmodel.DetallePeliculaViewModel
import com.example.damflix.viewmodel.DetallePeliculaViewModelFactory

sealed class Ruta(val path: String, val label: String, val icon: ImageVector) {
    object Perfil : Ruta(
        path = "perfil",
        label = "Perfil",
        icon = Icons.Filled.AccountCircle
    )

    object Listado : Ruta(
        path = "listado",
        label = "Mis Películas",
        icon = Icons.AutoMirrored.Filled.List
    )

    object Formulario : Ruta(
        path = "alta",
        label = "Añadir",
        icon = Icons.Filled.Add
    )

    object Detalle : Ruta("detalle/{peliculaId}", "Detalle", Icons.Filled.Details) {
        fun crearRuta(peliculaId: Int) = "detalle/$peliculaId"
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppDAMFlixMainScreen(viewModel: PeliculaViewModel) {
    val navController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    val opcionesMenu = listOf(Ruta.Perfil, Ruta.Formulario, Ruta.Listado)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                //Cabecera
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "DAMFlix - Room ORM",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                //Opciones del menu
                opcionesMenu.forEach { opcion ->
                    NavigationDrawerItem(
                        icon = { Icon(opcion.icon, contentDescription = opcion.label) },
                        label = { Text(opcion.label) },
                        selected = rutaActual == opcion.path,
                        onClick = {
                            //Ciere asíncrono del panel
                            coroutineScope.launch { drawerState.close() }
                            if (rutaActual != opcion.path) {
                                navController.navigate(opcion.path) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        //Contenido principal envuelto en el Scaffold
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("DAMFlix") },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir Menú")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Ruta.Perfil.path,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Ruta.Perfil.path) {
                    PerfilUsuario()
                }
                composable(Ruta.Listado.path) {
                    ListaPeliculasScreen(
                        viewModel = viewModel,
                        onPeliculaClick = { peliculaId ->
                            navController.navigate(Ruta.Detalle.crearRuta(peliculaId))
                        }
                    )
                }
                composable(Ruta.Formulario.path) {
                    FormularioPelicula(viewModel)
                }

                composable(
                    route = Ruta.Detalle.path,
                    arguments = listOf(
                        navArgument("peliculaId") {
                            type = NavType.IntType
                        }
                    )
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("peliculaId") ?: 0

                    val context = LocalContext.current
                    val app = context.applicationContext as DAMFlixApplication

                    val detalleViewModel: DetallePeliculaViewModel = viewModel(
                        factory = DetallePeliculaViewModelFactory(app.repository)
                    )
                    PantallaDetalleScreen(
                        peliculaId = id,
                        viewModel = detalleViewModel,
                        onVolver = { navController.popBackStack()}
                    )
                }
            }
        }
    }
}

