package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import uz.turgunboyevjurabek.fakestoreapi.R

@Composable
fun ImageWithStateHandling(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(true)
        .build()

    val imageLoader = ImageLoader.Builder(context)
        .error(R.drawable.ic_error)
        .placeholder(R.drawable.ic_image)
        .memoryCachePolicy(CachePolicy.ENABLED) // Xotira keshlashni yoqish
        .diskCachePolicy(CachePolicy.ENABLED) // Disk keshlashni yoqish
        .build()

    SubcomposeAsyncImage(
        model = request,
        imageLoader=imageLoader,
        contentDescription = "Dynamic Image",
        loading = {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        error = {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Tasvirni yuklashda xatolik yuz berdi!")
            }
        },
        modifier = modifier
            .fillMaxWidth()

    )
}
