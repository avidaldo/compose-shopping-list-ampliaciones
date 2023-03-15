package com.example.listacompra

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel


class ShoppingListViewModel : ViewModel() {

    private val _shoppingList = getItemList().toMutableStateList() // delegación?
    val shoppingList get() = _shoppingList

    private var _showAddDialog by mutableStateOf(false)
    val showAddDialog get() = _showAddDialog

    fun setShowAddDialog(value: Boolean) {
        _showAddDialog = value
    }

    private var _showRemoveDialog by mutableStateOf(false)
    val showRemoveDialog get() = _showRemoveDialog

    fun setShowRemoveDialog(value: Boolean) {
        _showRemoveDialog = value
    }

    fun addListElement(item: ShoppingListProduct) {
        shoppingList.find { item.productName == it.productName }?.let {
            throw ProductAlreadyExistsException()
        } ?: shoppingList.add(item)
    }

    class ProductAlreadyExistsException : RuntimeException()


    fun addListElement(shoppingListElementString: String) =
        addListElement(ShoppingListProduct(shoppingListElementString))


    fun removeListElement(item: ShoppingListProduct) {
        shoppingList.remove(item)
    }


    fun selectAll() {
        _shoppingList.map { it.isChecked = true }
    }


    fun removeCheckedItems() {
        _shoppingList.removeIf { it.isChecked }
    }

    fun isAnyChecked() = shoppingList.any { it.isChecked }

    fun areAllChecked() = shoppingList.all { it.isChecked }


    fun checkAll() {
        _shoppingList.map { it.isChecked = true }
    }

    fun checkNone() {
        _shoppingList.map { it.isChecked = false }
    }


}