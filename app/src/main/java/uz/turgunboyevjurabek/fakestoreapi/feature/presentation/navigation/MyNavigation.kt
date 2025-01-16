package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens.MainScreen
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens.SelectedItemScreen

@Composable
fun MyNavigation(modifier: Modifier = Modifier,navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainRout,
        modifier = modifier
    ) {
        composable<MainRout> {
            MainScreen(navHostController = navController)
        }
        composable<DetailRout> {
            val args = it.toRoute<DetailRout>()
            SelectedItemScreen(
                product = args,
                navHostController = navController
            )
        }

    }
}