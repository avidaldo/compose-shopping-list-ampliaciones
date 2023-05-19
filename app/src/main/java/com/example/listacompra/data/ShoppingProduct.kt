package com.example.listacompra.data

data class ShoppingProduct(val productName: String, val checked: Boolean = false)

fun getFakeShoppingProducts() = List(30) { i -> ShoppingProduct( "Producto $i") }