package com.handen.productlist.list

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Composable
fun ProductListScreen() {
    Text(text = "List")
}

@Composable
fun ItemProduct(product: Product) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberImagePainter(product.image),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
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
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.width(IntrinsicSize.Max)) {
            Text(
                text = "${product.price}$",
                fontSize = 16.sp,
                color = Color.Black
            )
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
