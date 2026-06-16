package com.example.stockpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockpro.viewmodel.StockViewModel
import com.example.stockpro.screens.PantallaCatalogo
import com.example.stockpro.screens.pantallaIngreso
import com.example.stockpro.screens.pantallaeditarstock
import com.example.stockpro.screens.Pantallareportefinanciero

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()

            val stockViewModel: StockViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = "ingreso"
            ) {
                composable("ingreso") {
                   pantallaIngreso(navController)
                }

                composable("catalogo/{nombre}") { backStackEntry ->

                    val nombre = backStackEntry
                        .arguments
                        ?.getString("nombre") ?: ""

                    PantallaCatalogo(
                        navController,
                        stockViewModel,
                        nombre
                    )
                }

                composable("editar/{id}") { backStackEntry ->

                    val id = backStackEntry
                        .arguments
                        ?.getString("id")
                        ?.toIntOrNull() ?: 0

                    pantallaeditarstock(
                        navController,
                        stockViewModel,
                        id
                    )
                }

                composable("reporte") {

                        Pantallareportefinanciero(
                        navController,
                        stockViewModel,
                    )
                }
            }
        }
    }
}