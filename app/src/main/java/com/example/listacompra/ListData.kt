package com.example.listacompra

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShoppingListProduct(val productName: String){
    var checked by mutableStateOf(false)
}
