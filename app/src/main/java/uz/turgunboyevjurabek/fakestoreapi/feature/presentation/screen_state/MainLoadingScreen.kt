package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screen_state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.ProductLoadingState

@Composable
fun MainLoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ){
        ProductLoadingState()
    }

}