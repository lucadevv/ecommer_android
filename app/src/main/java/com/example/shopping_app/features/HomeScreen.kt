package com.example.shopping_app.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shopping_app.core.theme.Shopping_appTheme


@Composable
fun HomeScreen(
    navController: NavController
){
    Scaffold {innerPadding->
        Body(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        )

    }
}

@Composable
private fun Body(modifier: Modifier){
    Column(
        modifier = modifier
    ) {
        Text("Home Screen")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview(){
    Shopping_appTheme {
        val navController = rememberNavController();
        HomeScreen(navController = navController)
    }
}