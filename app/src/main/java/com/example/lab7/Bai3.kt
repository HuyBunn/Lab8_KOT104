package com.example.lab7

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

enum class Screen(val route: String) {
    LOGIN("Login"),
    ADD("Add"),
    EDIT("Edit"),
    MOVIE_SCREEN("MovieScreen"),
    SCREEN1("Screen1"),
    SCREEN2("Screen2"),
    SCREEN3("Screen3"),
}
@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    val movieViewModel: MovieViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.LOGIN.route,
    ) {
        composable(Screen.LOGIN.route) { LoginScreen(navController) }
        composable(Screen.ADD.route) { MovieFormScreen(navController, movieViewModel, null) }
        composable(
            "${Screen.EDIT.route}/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.StringType }),
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("filmId")?.let { filmId ->
                MovieFormScreen(navController, movieViewModel, filmId)
            }
        }
        composable(Screen.MOVIE_SCREEN.route) { MovieScreen(navController, movieViewModel) }
//        composable(Screen.SCREEN1.route) { Screen1(navController) }
//        composable(Screen.SCREEN2.route) { Screen2(navController) }
//        composable(Screen.SCREEN3.route) { Screen3(navController) }
    }
}
