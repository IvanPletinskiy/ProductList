package com.handen.productlist.list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(list: List<Product>)

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<Product>
}