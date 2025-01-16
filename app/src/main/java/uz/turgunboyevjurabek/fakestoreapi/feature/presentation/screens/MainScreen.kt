@file:OptIn(ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens

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
import org.koin.androidx.compose.koinViewModel
import uz.turgunboyevjurabek.fakestoreapi.core.utils.Status.*
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.Product
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.MySearchView
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.ProductLoadingState
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.ProductsList
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screen_state.MainErrorState
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.view_model.GetProductsViewModel

@Composable
fun MainScreen(
    viewModel: GetProductsViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val productsList = remember { mutableStateListOf<Product>() }
    var isProductsLoading by rememberSaveable { mutableStateOf(false) }

    /**
     * Get all products
     */
    val getData by viewModel.getData.collectAsStateWithLifecycle()
    when (getData.status) {
        DEFAULT -> {}
        LOADING -> {
            isProductsLoading=true
        }
        ERROR -> {
            MainErrorState(errorMessage = getData.message.toString())
            isProductsLoading=false
        }
        SUCCESS -> {
            isProductsLoading=false
            LaunchedEffect(Unit) {
                getData.data?.let {
                    productsList.addAll(it.products)
                }

            }
        }
    }

    /**
     * UI layer
     */
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Discover",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Black
                        )
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Rounded.Notifications, contentDescription = null)
                    }
                },
                modifier = modifier
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
                modifier=modifier
                    .zIndex(10f)
                    .fillMaxWidth()
            ) {
                Column {
                    MySearchView()
                    Spacer(modifier=modifier.height(10.dp))
                    Text("All products",fontWeight = FontWeight.Bold,fontFamily = FontFamily.SansSerif,fontSize = 18.sp,modifier = modifier.padding(16.dp))
                }
            }
            when(isProductsLoading){
                true -> ProductLoadingState()
                false -> ProductsList(products = productsList)
            }

        }
    }

}

