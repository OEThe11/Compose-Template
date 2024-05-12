package com.example.composetemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetemplate.screens.details.DetailsScreen
import com.example.composetemplate.screens.main.MainScreen


@Composable
fun StandardNavi() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StandardScreens.MainScreen.name
    ){
        composable(StandardScreens.MainScreen.name){
            MainScreen(navController = navController)
        }

        composable(
            "${StandardScreens.DetailsScreen.name}/{id}",
            arguments = listOf(navArgument("id"){ type = NavType.IntType})
        ){backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            id?.let {
                DetailsScreen(
                    id = it,
                    navController = navController
                )
            }

        }
    }
}