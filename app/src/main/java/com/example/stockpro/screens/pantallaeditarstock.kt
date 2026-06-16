package com.example.stockpro.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun pantallaeditarstock(
    navController: NavController,
    viewModel: StockViewModel,
    id: Int
) {

    val producto =
        viewModel.obtenerProducto(id)

    var stock by remember {
        mutableStateOf(
            producto?.stockActual ?: 0
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            producto?.nombre ?: "",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            producto?.descripcion ?: ""
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Stock Actual: $stock",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {

            Button(
                onClick = {
                    stock++
                }
            ) {
                Text("+1")
            }

            Spacer(
                modifier = Modifier.width(15.dp)
            )

            Button(
                onClick = {
                    stock--
                },
                enabled = stock > 0
            ) {
                Text("-1")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                viewModel.actualizarStock(
                    id,
                    stock
                )

                navController.popBackStack()
            }
        ) {
            Text("Guardar y Volver")
        }
    }
}