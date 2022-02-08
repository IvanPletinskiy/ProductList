package com.handen.productlist.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel: ViewModel() {

    val state: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    init {
        state.value = State.LoadedFromCache(listOf(Product(
            1,
            "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            category = "men's clothing",
            price = "109.95",
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
        )))
//        state.value = State.ConnectionError
        viewModelScope.launch {

        }
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val products: List<Product>): State()
        data class LoadedFromCache(val products: List<Product>): State()
        object ConnectionError: State()
    }
}