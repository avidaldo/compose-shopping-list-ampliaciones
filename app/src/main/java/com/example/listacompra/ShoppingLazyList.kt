package com.example.listacompra

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShoppingLazyList(
    list: List<ShoppingListProduct>,
    onChangeChecked: (ShoppingListProduct) -> Unit,
    onRemoveItem: (ShoppingListProduct) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        items(items = list) { product ->
            ShoppingListItem(
                productName = product.productName,
                checked = product.checked,
                onChangeChecked = { onChangeChecked(product) },
                onClose = {
                    onRemoveItem(product)
                })
        }

    }
}