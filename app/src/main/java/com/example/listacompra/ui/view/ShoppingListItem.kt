package com.example.listacompra.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listacompra.R

@Composable
fun ShoppingListItem(
    productName: String,
    checked: Boolean,
    onChangeChecked: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 8.dp
    ) {
        Row(
            Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                productName,
                Modifier
                    .weight(1F)
                    .padding(start = 16.dp),
                fontSize = 25.sp
            )

            Checkbox(
                checked = checked,
                onCheckedChange = onChangeChecked
            )

            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = stringResource(R.string.close))
            }
        }
    }
}


