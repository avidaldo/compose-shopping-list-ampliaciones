package com.example.listacompra.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShoppingProduct(val productName: String){
    var checked by mutableStateOf(false)
}


fun getFakeShoppingProducts() = List(30) { i -> ShoppingProduct( "Producto $i") }