package com.example.stockpro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavController
import com.example.stockpro.viewmodel.StockViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
@Composable
fun PantallaCatalogo( navController: NavController,   //PERMITE LA NAVEGACION ENTRE PANTALLAS
    viewModel: StockViewModel,    //DA INFORMACION DE DATOS Y FUNCIONES DEL INVENTARIO
    nombre: String    // ES EL NOMBRE DEL OPERARIO
) {

    var criticos by remember {  // VARIABLE CRITICOS QUE MUESTRA PRODCUTOS SOLO DE BAJO STOCK
        mutableStateOf(false)
    }

    val lista =
        if (criticos)
            viewModel.obtenerProductosEnRiesgo()
        else
            viewModel.productos  //MUESTRA PRODUCTOS CON MENSO DE 5 UND

    Scaffold(

        floatingActionButton = {  // boton flotante navegar entre a pantalla del  sistemas

            FloatingActionButton(
                onClick = {
                    navController.navigate("reporte")
                }
            ) {
                Text("REPORTE")
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(10.dp)


        ) {

            Text(
                text = "Operario: $nombre", // se muestra el nombre del operacion que dio incio para ver quien utiliza el sistema
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row {

                Button( //nuestra todos los productos
                    onClick = {
                        criticos = false
                    }
                ) {
                    Text("Ver Todo")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(   // muestra prodcutos bajos en stock
                    onClick = { criticos = true }
                ) {
                    Text("Stock Crítico")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {  // muestra lista de productos

                items(lista) { producto ->

                    Card(  //muestra dentro de una tarjeta el nombre, precio y cantidad
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clickable { // selecciona producto para  navegar dento de la pantalla de idicion y editar el stock
                                navController.navigate(
                                    "editar/${producto.id }"
                                )
                            }
                    ) {

                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {

                            Text(producto.nombre)

                            Text(
                                "Precio: $${producto.precio}"
                            )

                            Text(
                                text = "Stock: ${producto.stockActual}",
                                color =
                                    if (producto.stockActual < 5)  // Cuando es stock es menor a 5 unid el texto se muestra en rojo para alertar
                                        Color.Red
                                    else
                                        Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}