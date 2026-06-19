package com.example.stockpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
@Composable
fun pantallaIngreso( navController: NavController){

    var nombre by remember{ mutableStateOf("") }  /// nombre ingresado temporalente mientras la pantalla este activa

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "BIENVENIDO A STOCKPRO",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(    //este permite ingresar tu nombre
            value = nombre,
            onValueChange = {
                nombre = it
            },
            label = {
                Text(
                    text = "Nombre Operario",
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(  // boton permite acceder al sistema
            onClick = {
                navController.navigate("catalogo/$nombre") // esto me permitira nacegar a la siguiente pantalla
            },
            enabled = nombre.length >= 3 // boton se libera solo cuando este mas de tres caracteres
        ) {
            Text("Ingresar al Sistema")
        }
    }}









