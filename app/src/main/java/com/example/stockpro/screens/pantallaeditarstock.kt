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

    val producto =      // BUSCA EL RPDUCTO POR EL ID RECIBIDO DESDE LA PANTALLA CATALOGO
        viewModel.obtenerProducto(id)

    var stock by remember {  //CREAR UNA VARIABLE PARA ALMACENAR LA CANTIDAD ACTUAL DEL PRODUCTO
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
            style = MaterialTheme.typography.headlineMedium //MUESTRA EL NOMRE DE PRODUCTO
        )

        Text(
            producto?.descripcion ?: ""
        )

        Spacer(modifier = Modifier.height(20.dp)) // MUESTRA DESCRIPCION DEL PRODUCTO

        Text(
            "Stock Actual: $stock",
            style = MaterialTheme.typography.headlineSmall // MOSTRAR EL STOCK ACTUAL MIESTRAS SE REALIZAN MODIFICACIONES
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {

            Button(  // INCREMENTA LA CANTIDAD DISPONIBLE DEL PRODUCTO
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
                onClick = { // DISMINIUYE LA CANTIDAD DEL PRODCUTO
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

                viewModel.actualizarStock( //ACTUALIZA LOS DATOS
                    id,
                    stock
                )

                navController.popBackStack() // REGRESA AL CATALOGO AUTOMATICAMENTE
            }
        ) {
            Text("Guardar y Volver")
        }
    }
}