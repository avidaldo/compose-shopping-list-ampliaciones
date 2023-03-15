package com.example.listacompra

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingLazyList(
    list: List<ShoppingListProduct>,
    removeElement: (ShoppingListProduct) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier
            .padding(top = 20.dp)
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = list) { element ->
            ShoppingListItem(
                element,
                onClose = {
                    removeElement(element)
                })
        }

    }
}