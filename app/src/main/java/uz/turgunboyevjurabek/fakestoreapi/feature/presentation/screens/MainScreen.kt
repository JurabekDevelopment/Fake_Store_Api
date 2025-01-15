package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import uz.turgunboyevjurabek.fakestoreapi.core.utils.Status.*
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screen_state.MainErrorScreen
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screen_state.MainLoadingScreen
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screen_state.MainSuccessScreen
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.view_model.GetProductsViewModel

@Composable
fun MainScreen(
    viewModel: GetProductsViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val getData by viewModel.getData.collectAsStateWithLifecycle()
    Column(modifier = modifier.fillMaxSize()) {
        when (getData.status) {
            DEFAULT -> {}
            LOADING -> {
                MainLoadingScreen()
            }
            ERROR -> {
                MainErrorScreen(errorMessage = getData.message.toString())
            }
            SUCCESS -> {
                getData.data?.let {
                    MainSuccessScreen(
                        data = it
                    )
                }
            }
        }

    }

}

