package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.koin.androidx.compose.koinViewModel
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens.MainScreen
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens.SelectedItemScreen
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.view_model.GetProductsViewModel

@Composable
fun MyNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    productsViewModel: GetProductsViewModel = koinViewModel()
    ) {
    NavHost(
        navController = navController,
        startDestination = MainRout,
        modifier = modifier
    ) {
        composable<MainRout> {
            MainScreen(
                navHostController = navController,
                viewModel = productsViewModel,
            )
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