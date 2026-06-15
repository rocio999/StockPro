package com.example.stockpro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

import com.example.stockpro.model.Producto

class StockViewModel: ViewModel (){

        val productos = mutableStateListOf(
            Producto(
                1,
                "Refrigeradora",
                "Mantiene alimentos frescos sin generar escarcha",
                1500.0,
                15
            ),
            Producto(2, "Cocina", "Equipo estandarpara cocinar alimentos", 500.0, 10),
            Producto(3, "Horno de Microondas", "Calenta y descongela alimentos", 100.0, 9),
            Producto(4, "Licuadora", "Procesa verduras, frutas e incluso tritura hielo", 45.0, 7),
            Producto(5, "Freidora de aire", "Cocina alimentos crujientes, sin aceite", 140.0, 12),
            Producto(6, "Lavadora", "Lava, enjuaga y centrifuga la ropa", 650.0, 2),
            Producto(
                7,
                "Aspiradora robot",
                "Limpia los pisos de manera autonoma programable",
                350.0,
                5
            ),
            Producto(8, "Ventilador", "Refresca en tres niveles", 50.0, 3)
        )

        fun obtenerProducto(id: Int): Producto? {
            return productos.find { it.id == id }
        }

        fun actualizarStock(id: Int, nuevacantidad: Int) {
            val index = productos.indexOfFirst { it.id == id }

            if (index != -1) {
                productos[index] =
                    productos[index].copy(
                        stockActual = nuevacantidad
                    )
            }
        }

        fun calcularValorTotalInventario(): Double {
            return productos.sumOf { it.precio * it.stockActual }
        }

        fun obtenerProductosEnRiesgo(): List<Producto> {
            return productos.filter { it.stockActual < 5 }

        }

        fun totalProductosSinStock(): Int {
            return productos.count { it.stockActual == 0 }
        }
    }
