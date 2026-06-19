package com.example.stockpro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

import com.example.stockpro.model.Producto

class StockViewModel: ViewModel (){  // administra los datos y realizan operaciones.

        val productos = mutableStateListOf(  // Almacena lista
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

        fun obtenerProducto(id: Int): Producto? {      //busca su producto pro identificador y devuelve el producto encontrado
            return productos.find { it.id == id }
        }

        fun actualizarStock(id: Int, nuevacantidad: Int) {   //permite modificar la cantidad disponible del producto
            val index = productos.indexOfFirst { it.id == id }

            if (index != -1) {
                productos[index] =
                    productos[index].copy(
                        stockActual = nuevacantidad
                    )
            }
        }

        fun calcularValorTotalInventario(): Double {    // calcula el valor economico total del inventario multiplicando el precio por cada producto
            return productos.sumOf { it.precio * it.stockActual }
        }

        fun obtenerProductosEnRiesgo(): List<Producto> {   // devuelve la lista de productos cuya unidad es menor a 5 unidades
            return productos.filter { it.stockActual < 5 }

        }

        fun totalProductosSinStock(): Int {   // cuantos prodcutos tienen stock igual a cero y devuelve esa cantidad
            return productos.count { it.stockActual == 0 }
        }
    }
