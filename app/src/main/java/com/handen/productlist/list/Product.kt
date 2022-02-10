package com.handen.productlist.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(@PrimaryKey val id: Int, val title: String, val price: String, val category: String, val description: String, val image: String)