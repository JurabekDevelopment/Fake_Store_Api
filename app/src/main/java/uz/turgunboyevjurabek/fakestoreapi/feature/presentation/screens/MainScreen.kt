package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import uz.turgunboyevjurabek.fakestoreapi.core.utils.Status
import uz.turgunboyevjurabek.fakestoreapi.core.utils.Status.*
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.MainErrorScreen
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.MainLoadingScreen
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.MainSuccessScreen
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.view_model.GetProductsViewModel

@Composable
fun MainScreen(
    viewModel: GetProductsViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val getData by viewModel.getData.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {

            when(getData.status){
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

}
