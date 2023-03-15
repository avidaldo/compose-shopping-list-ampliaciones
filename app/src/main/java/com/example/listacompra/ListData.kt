package com.example.listacompra

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class ShoppingListProduct(val productName: String){
    var isChecked by mutableStateOf(false)
}

fun getItemList() = listOf(
    ShoppingListProduct(
        "Producto 1"
    ),
    ShoppingListProduct(
        "Producto 2"
    ),
    ShoppingListProduct(
        "Producto 3"
    ),
    ShoppingListProduct(
        "Producto 4"
    ),
    ShoppingListProduct(
        "Producto 5"
    )
)