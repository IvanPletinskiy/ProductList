package com.handen.productlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.handen.productlist.list.ProductListScreen
import com.handen.productlist.ui.theme.Grey
import com.handen.productlist.ui.theme.ProductListTheme
import com.handen.productlist.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductListApp()
        }
    }
}

@Composable
fun ProductListApp() {
    val navController = rememberNavController()
    ProductListTheme {
        Column {
            NavHost(navController = navController, startDestination = "product_list", modifier = Modifier.weight(1f)) {
                composable("profile") { ProfileScreen() }
                composable("product_list") {
                    ProductListScreen()
                }
                composable("about") {
                    AboutScreen()
                }
            }
            BottomNavigationBar(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductListApp()
}

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val items = listOf(
        NavigationItem("Profile", "profile", Icons.Filled.Person),
        NavigationItem("Products", "product_list", Icons.Filled.List),
        NavigationItem("About", "about", Icons.Filled.Info)
    )
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Purple500,
                unselectedContentColor = Grey.copy(0.6f),
                alwaysShowLabel = true,
                selected = item.route == navBackStackEntry?.destination?.route,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}


data class NavigationItem(val title: String, val route: String, val icon: ImageVector)