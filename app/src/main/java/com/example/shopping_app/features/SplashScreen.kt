package com.example.shopping_app.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shopping_app.core.app_routes.HomeRoute
import com.example.shopping_app.core.app_routes.SplashRoute
import com.example.shopping_app.core.theme.Shopping_appTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(HomeRoute) {
            popUpTo(SplashRoute) { inclusive = true }
        }
    }
    Scaffold { paddingValues ->
        Body(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}

@Composable
private fun Body(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Shopping Store",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold
            )

        )
        Icon(
            imageVector = Icons.Filled.Star, contentDescription = "icon",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.height(50.dp).width(50.dp)
        )


    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewSplash() {
    Shopping_appTheme {
        val navController = rememberNavController()
        SplashScreen(navController = navController)
    }
}