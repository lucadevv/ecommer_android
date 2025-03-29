package com.example.shopping_app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shopping_app.core.app_routes.HomeRoute
import com.example.shopping_app.core.app_routes.SplashRoute
import com.example.shopping_app.core.theme.Shopping_appTheme
import com.example.shopping_app.features.HomeScreen
import com.example.shopping_app.features.SplashScreen


@Composable
fun App(
 navController: NavHostController
){
   Shopping_appTheme {
       NavHost(navController = navController, startDestination = SplashRoute) {
           composable<SplashRoute>{
               SplashScreen(navController = navController)
           }
           composable<HomeRoute> {
               HomeScreen(navController = navController)
           }
       }

   }
}