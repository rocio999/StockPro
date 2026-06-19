package com.example.stockpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.stockpro.ui.theme.StockProTheme
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
            StockProTheme {

            val navController = rememberNavController()  //permite moverse entre las distintas pantallas

            val stockViewModel: StockViewModel = viewModel() //contiene la informacion sincronica

            NavHost( // conectar pantallas
                navController = navController, // se establece la primera pantalla que vera el usuario
                startDestination = "ingreso"
            ) {
                composable("ingreso") { // muestra la pantalla donde ingresa el nombre el operario
                   pantallaIngreso(navController)
                }
                        // recibe el nomre ingresado por el operario y lo envia a la pantalla de catalogo
                composable("catalogo/{nombre}") { backStackEntry ->

                    val nombre = backStackEntry
                        .arguments
                        ?.getString("nombre") ?: ""  // aqui se recupera el parametro enviado desde la pantalla anterior

                    PantallaCatalogo(
                        navController,
                        stockViewModel,
                        nombre
                    )
                }
                    //identificador del producto
                composable("editar/{id}") { backStackEntry ->

                    val id = backStackEntry
                        .arguments
                        ?.getString("id")
                        ?.toIntOrNull() ?: 0  // obtiene el id del producto para cargar sus datos y permite la modificacion del mismo

                    pantallaeditarstock(
                        navController,
                        stockViewModel,
                        id
                    )
                }

                composable("reporte") { // accede al reporte financiero

                        Pantallareportefinanciero(
                        navController,
                        stockViewModel,
                    )
                }
            }
        }
    }
}}