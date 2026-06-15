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

@Composable
fun PantallaCatalogo(
    navController: NavController,
    viewModel: StockViewModel,
    nombre: String
) {

    var criticos by remember {
        mutableStateOf(false)
    }

    val lista =
        if (criticos)
            viewModel.obtenerProductosEnRiesgo()
        else
            viewModel.productos

    Scaffold(

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("reporte")
                }
            ) {
                Text("R")
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(10.dp)
        ) {

            Text(
                text = "Operario: $nombre",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row {

                Button(
                    onClick = {
                        criticos = false
                    }
                ) {
                    Text("Ver Todo")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = {
                        criticos = true
                    }
                ) {
                    Text("Stock Crítico")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {

                items(lista) { producto ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clickable {
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
                                    if (producto.stockActual < 5)
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