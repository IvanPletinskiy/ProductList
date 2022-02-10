package com.handen.productlist.list

import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import javax.inject.Inject

interface RemoteDataService {
    suspend fun getProducts(): List<Product>
}

class RemoteDataServiceImpl @Inject constructor(private val api: FakeStoreApi) : RemoteDataService {

    override suspend fun getProducts(): List<Product> {
        val response = api.getJewelery()
        if(response.isSuccessful && response.body() != null) {
            return response.body() ?: throw IllegalStateException("response body is null")
        } else {
            println(response.errorBody()?.string())
            throw IllegalStateException()
        }
    }
}