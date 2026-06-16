package com.example.stockpro.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import com.example.stockpro.viewmodel.StockViewModel


@Composable
fun Pantallareportefinanciero(
    navController: NavController,
    viewModel: StockViewModel
) {

    val total =
        viewModel.calcularValorTotalInventario()

    val agotados =
        viewModel.totalProductosSinStock()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "Capital Invertido Total",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            "$$total",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            "Productos con stock cero: $agotados"
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Volver")
        }
    }
}

