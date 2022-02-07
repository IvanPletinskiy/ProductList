package com.handen.productlist.list

class ProductListViewModel {

    sealed class State {
        object Loading : State()
        data class Loaded(val products: List<Product>)
    }
}