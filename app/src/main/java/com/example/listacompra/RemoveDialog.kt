package com.example.listacompra

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun RemoveDialog( // https://www.youtube.com/watch?v=2rCyXaYkTp0
    titleString: String,
    confirmString: String,
    dismissString: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        title = { Text(text = titleString) },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text(text = confirmString)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = dismissString)
            }
        }
    )
}