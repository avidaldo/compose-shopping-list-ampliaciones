package com.example.listacompra

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MainScreen() {
    val viewModel: ShoppingListViewModel = viewModel()

    val context = LocalContext.current

    Scaffold(scaffoldState = rememberScaffoldState(), topBar = {
        TopAppBar(title = {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(R.string.titulo), Modifier.weight(1F))
//                    BasicTextField(value = viewModel.listTitle, onValueChange = {viewModel.listTitle = it})
                if (viewModel.isAnyChecked()) {
                    Text(text = "Select All", fontSize = 10.sp)
                    Checkbox(checked = viewModel.areAllChecked(), onCheckedChange = {
                        if (viewModel.areAllChecked()) viewModel.checkNone()
                        else (viewModel.checkAll())
                    })
                }
            }
        }, actions = {

            if (viewModel.isAnyChecked()) {
                IconButton(onClick = { viewModel.setShowRemoveDialog(true)}) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Eliminar")
                }
            }
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.setShowAddDialog(true) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add item")
        }
    }) { paddingValues ->
        ShoppingLazyList(
            list = viewModel.shoppingList,
            removeElement = { viewModel.removeListElement(it) },
            modifier = Modifier.padding(paddingValues),
        )
    }

    if (viewModel.showAddDialog) {
        AddDialog(
            titleString = "Add new element",
            confirmString = "Confirmar",
            dismissString = "Cancelar",
            onConfirm = {
                try {
                    viewModel.addListElement(it)
                    viewModel.setShowAddDialog(false)
                } catch (e: ShoppingListViewModel.ProductAlreadyExistsException) {
                    Toast.makeText(context, "Ese producto ya existe.", Toast.LENGTH_SHORT).show()
                }
            },
            onDismiss = { viewModel.setShowAddDialog(false) },
        )
    }
    else if (viewModel.showRemoveDialog) {
        RemoveDialog(
            titleString = "¿Eliminar productos?",
            confirmString = "Confirmar",
            dismissString = "Cancelar",
            onConfirm = {
                    viewModel.removeCheckedItems()
                    viewModel.setShowRemoveDialog(false)
            },
            onDismiss = { viewModel.setShowRemoveDialog(false) },
        )
    }

}



