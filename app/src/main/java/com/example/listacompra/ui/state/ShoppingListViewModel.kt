package com.example.listacompra.ui.state

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.listacompra.data.ShoppingProduct
import com.example.listacompra.data.getFakeShoppingProducts


class ShoppingListViewModel : ViewModel() {

    private val _shoppingList = getFakeShoppingProducts().toMutableStateList() // para testear

    //private val _shoppingList = mutableStateListOf<ShoppingProduct>()
    val shoppingList: List<ShoppingProduct> get() = _shoppingList
    // https://stackoverflow.com/questions/68261797/private-setter-vs-backing-property-in-kotlin

    private var _showAddDialog by mutableStateOf(false)
    val showAddDialog get() = _showAddDialog
    fun setAddDialog(value: Boolean) {
        _showAddDialog = value
    }

    private var _showRemoveDialog by mutableStateOf(false)
    val showRemoveDialog get() = _showRemoveDialog
    fun setsRemoveDialog(value: Boolean) {
        _showRemoveDialog = value
    }


    private fun addProduct(item: ShoppingProduct) =
        _shoppingList.add(item)

    fun addProduct(productString: String) =
        if (shoppingList.none { productString == it.productName })
            addProduct(ShoppingProduct(productString))
        else false  // (1)

    fun addProductImperativeStyle(productString: String): Boolean {
        if (shoppingList.none { productString == it.productName })
            return addProduct(ShoppingProduct(productString))
        else return false  // (1)
    }


    fun removeProduct(item: ShoppingProduct) {
        _shoppingList.remove(item)
    }

    fun removeCheckedProducts() {
        _shoppingList.removeIf { it.checked }
    }

    fun checkAll() {
        repeat(_shoppingList.size) {
            _shoppingList[it] = _shoppingList[it].copy(checked = true)
        }
    }

    fun unCheckAll() {
        repeat(_shoppingList.size) {
            _shoppingList[it] = _shoppingList[it].copy(checked = false)
        }
    }

    fun changeChecked(product: ShoppingProduct) {
        val index = _shoppingList.indexOf(product)
        _shoppingList[index] =
            _shoppingList[index].copy(checked = !_shoppingList[index].checked)
    }

}


/**
 * Aplico la lógica de las collections en Java: el método add de cualquier colection devuelve true
 * si esa colección de datos ha cambiado tras la operación. En una lista, siempre devuelde true, pero
 * en un Set (conjunto) devolvería false si ese elemento ya pertenece al Set ya que, por definición,
 * un conjunto no permite elementos duplicados.
 *
 * https://stackoverflow.com/questions/24173117/why-does-list-adde-return-boolean-while-list-addint-e-returns-void
 *
 * Por eso en este caso opto por devolver false si el elemento ya devuelve en la lista.
 *
 * Podría hacerse más explícito utilizando una excepción personalizada:
 *

fun addListElement(item: ShoppingListProduct) {
shoppingList.find { item.productName == it.productName }?.let {
throw ProductAlreadyExistsException()
} ?: shoppingList.add(item)
}
class ProductAlreadyExistsException : RuntimeException()

 *
 * Además, para mejorar la legibilidad, en lugar de find, utilizo la función none:
 * https://kotlinlang.org/docs/collection-filtering.html#test-predicates
 *
 * Se está, por tanto, utilizando una lista y superponiéndole la lógica de un Set. Cabría plantearse
 * entonces utilizar directamente un Set. Sin embargo, no he encontrado que exista un "mutableStateSet".
 *
 * Imagino que será posible utilizar un Set encapsulado con LiveData o Flows, pero aún no lo hemos
 * visto (TODO)
 *
 */

