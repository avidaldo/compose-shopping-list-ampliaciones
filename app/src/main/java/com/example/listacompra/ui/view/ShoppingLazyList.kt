package com.example.listacompra.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listacompra.data.ShoppingProduct

@Composable
fun ShoppingLazyList(
    list: List<ShoppingProduct>,
    onChangeChecked: (ShoppingProduct) -> Unit,
    onRemoveItem: (ShoppingProduct) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier.padding(10.dp)) {
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