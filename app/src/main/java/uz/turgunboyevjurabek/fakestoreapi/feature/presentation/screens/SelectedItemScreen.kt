@file:OptIn(ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import uz.turgunboyevjurabek.fakestoreapi.R
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.Product
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components.ImageWithStateHandling
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.navigation.DetailRout

@Composable
fun SelectedItemScreen(
    navHostController: NavHostController,
    product: DetailRout,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier
                            .size(45.dp)
                            .padding(end = 10.dp)
                            .clip(CircleShape)
                    ) {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                    }
                },
                actions = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier
                            .size(45.dp)
                            .padding(end = 10.dp)
                            .clip(Shapes().small)
                    ) {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_favorite_border),
                                contentDescription = null
                            )
                        }
                    }
                },
                modifier = modifier
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Surface(
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
            ) {
                Column(modifier = modifier.fillMaxSize()) {
                    ImageWithStateHandling(
                        imageUrl = product.image,
                        modifier = modifier
                    )
                }
            }
            Spacer(modifier.height(20.dp))
            Text(
                text = product.title,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier.height(10.dp))
            Text(
                product.price.toString()+"$",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Black
                )
            )
            Spacer(modifier.height(20.dp))
            Text(
                text = product.description,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal
                )
            )

        }
    }
}