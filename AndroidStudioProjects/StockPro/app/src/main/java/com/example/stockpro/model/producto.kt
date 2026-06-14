package com.example.stockpro.model

class producto {
    data class Producto(
        val id:Int,
        val nombre: String,
        val descripcion: String,
        val precio:Double,
        val stockActual:Int,
    )



}