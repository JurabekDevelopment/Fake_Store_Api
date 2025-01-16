@file:OptIn(ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import uz.turgunboyevjurabek.fakestoreapi.core.utils.Status.*
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.Product
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.MySearchView
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.ProductLoadingState
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.ProductsList
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.navigation.DetailRout
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.view_model.GetProductsViewModel

@Composable
fun MainScreen(
    navHostController: NavHostController,
    viewModel: GetProductsViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val productsList = remember { mutableStateListOf<Product>() }
    var isProductsLoading by rememberSaveable { mutableStateOf(true) }

    // Observe data
    val getData by viewModel.getData.collectAsStateWithLifecycle()

    LaunchedEffect(getData.status) {
        when (getData.status) {
            DEFAULT, LOADING -> {
                isProductsLoading = true
            }

            ERROR -> {
                isProductsLoading = false
            }

            SUCCESS -> {
                isProductsLoading = false
                productsList.clear()
                getData.data?.let { productsList.addAll(it.products) }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Discover", style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Black
                    )
                )
            }, actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.Notifications, contentDescription = null)
                }
            }, modifier = modifier
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            Surface(
                shadowElevation = 10.dp,
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                modifier = modifier
                    .zIndex(10f)
                    .fillMaxWidth()
            ) {
                Column {
                    MySearchView()
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        "All products",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        modifier = modifier.padding(16.dp)
                    )
                }
            }

            AnimatedContent(targetState = isProductsLoading,
                transitionSpec = {
                    slideInVertically(
                        animationSpec = tween(500),
                        initialOffsetY = { it }) togetherWith slideOutVertically(
                        animationSpec = tween(500),
                        targetOffsetY = { -it }
                    )
                }) {
                if (it) {
                    ProductLoadingState()
                } else {
                    ProductsList(
                        onItemClick = { product ->
                            navHostController.navigate(
                                DetailRout(
                                    id = product.id,
                                    title = product.title,
                                    price = product.price,
                                    description = product.description,
                                    category = product.category,
                                    image = product.image,
                                    brand = product.brand,
                                    discount = product.discount,
                                    onSale = product.onSale,
                                    popular = product.popular,
                                    color = product.color,
                                    model = product.model
                                )
                            )
                        },
                        products = productsList
                    )
                }

            }
        }
    }
}
