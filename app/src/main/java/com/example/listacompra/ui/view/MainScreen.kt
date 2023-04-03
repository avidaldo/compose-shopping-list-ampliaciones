package com.example.listacompra.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listacompra.R
import com.example.listacompra.ui.state.ShoppingListViewModel


@Composable
fun MainScreen() {
    val viewModel: ShoppingListViewModel = viewModel()

    Scaffold(
        scaffoldState = rememberScaffoldState(),  // TODO
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title)) },
                actions = {
                    if (viewModel.shoppingList.isNotEmpty()) {
                        Text(text = stringResource(R.string.select_all), fontSize = 10.sp)
                        Checkbox(
                            checked = viewModel.shoppingList.all { it.checked },
                            onCheckedChange = {
                                if (viewModel.shoppingList.all { it.checked }) viewModel.unCheckAll()
                                else (viewModel.checkAll())
                            })
                    }
                    if (viewModel.shoppingList.any { it.checked }) {
                        IconButton(onClick = { viewModel.setsRemoveDialog(true) }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = stringResource(R.string.delete)
                            )
                        }
                    }

                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.setAddDialog(true) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add)
                )
            }
        }) { paddingValues ->
        ShoppingLazyList(
            list = viewModel.shoppingList,
            onChangeChecked = { viewModel.changeChecked(it) },
            onRemoveItem = { viewModel.removeProduct(it) },
            modifier = Modifier.padding(paddingValues),
        )
    }

    if (viewModel.showAddDialog) {
        val context = LocalContext.current
        val stringForToast = stringResource(R.string.product_already_exists)
        TextFieldDialog(
            onConfirm = {
                if (!viewModel.addProduct(it))
                    Toast.makeText(context, stringForToast, Toast.LENGTH_SHORT).show()
            },
            onDismiss = { viewModel.setAddDialog(false) },
        )
    } else if (viewModel.showRemoveDialog) {
        ConfirmDialog(
            onConfirm = {
                viewModel.removeCheckedProducts()
            },
            onDismiss = { viewModel.setsRemoveDialog(false) },
        )
    }

}