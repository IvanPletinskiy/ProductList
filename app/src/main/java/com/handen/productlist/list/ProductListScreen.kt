package com.handen.productlist.list

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.handen.productlist.list.ProductListViewModel.State.ConnectionError
import com.handen.productlist.list.ProductListViewModel.State.Loading
import com.handen.productlist.ui.theme.Grey

@Composable
fun ProductListScreen(viewModel: ProductListViewModel = viewModel()) {
    Text(text = "List")
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(56.dp),
                backgroundColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Clothes",
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                }
            }
        }
    ) {
        val state by viewModel.state.collectAsState()
        when (state) {
            is Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Text(text = "Loading...")
                    }
                }
            }
            is ConnectionError -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(modifier = Modifier.size(32.dp), imageVector = Icons.Outlined.Warning, contentDescription = null)
                        Text(text = "Check your internet connection")
                    }
                }
            }
            is ProductListViewModel.State.Loaded -> {
                val products = (state as ProductListViewModel.State.Loaded).products
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                        items(products) {
                            ItemProduct(product = it)
                        }
                    }
                }
            }
            is ProductListViewModel.State.LoadedFromCache -> {
                val products = (state as ProductListViewModel.State.LoadedFromCache).products
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .background(color = Color(0xfffffde7))
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    ) {
                        Text(
                            text = "Loaded from cache",
                            fontSize = 16.sp,
                            color = Grey,
                        )
                    }
                    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                        items(products) {
                            ItemProduct(product = it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemProduct(product: Product) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .height(64.dp)
    ) {
        Image(
            painter = rememberImagePainter(product.image),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            Modifier
                .weight(1f)
                .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.title,
                    fontSize = 16.sp,
                    color = Color.Black, overflow = TextOverflow.Ellipsis, maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.category,
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.height(IntrinsicSize.Max),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${product.price}$",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemProductPreview() {
    ItemProduct(
        product = Product(
            1,
            "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            category = "men's clothing",
            price = "109.95",
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ProductListPreview() {
    ProductListScreen()
}
