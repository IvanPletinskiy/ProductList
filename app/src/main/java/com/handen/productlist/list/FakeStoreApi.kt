package com.handen.productlist.list

import retrofit2.Response
import retrofit2.http.GET


interface FakeStoreApi {
    @GET("products/category/jewelery")
    suspend fun getJewelery(): Response<List<Product>>
}