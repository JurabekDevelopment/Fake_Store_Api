@file:OptIn(ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
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
    Scaffold() { innerPadding ->
        ConstraintLayout(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            val (surface, title, company, price, category, description) = createRefs()
            Surface(
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .constrainAs(surface) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                ConstraintLayout(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    val (image, back, favorite) = createRefs()
                    /**
                     * Back Button
                     */
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier
                            .clickable { }
                            .size(45.dp)
                            .background(MaterialTheme.colorScheme.background, Shapes().large)
                            .clip(Shapes().small)
                            .constrainAs(back) {
                                top.linkTo(parent.top, 12.dp)
                                start.linkTo(parent.start, 12.dp)
                            }
                    ) {
                        IconButton(onClick = {
                            if (navHostController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
                                navHostController.popBackStack()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                contentDescription = null,
                                modifier = modifier
                                    .size(30.dp)
                            )
                        }
                    }

                    /**
                     * Like Button
                     */
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier
                            .clickable { }
                            .size(45.dp)
                            .background(MaterialTheme.colorScheme.background, Shapes().large)
                            .clip(Shapes().small)
                            .constrainAs(favorite) {
                                top.linkTo(parent.top, 12.dp)
                                end.linkTo(parent.end, 12.dp)
                            }
                    ) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_favorite_border),
                                contentDescription = null,
                                modifier = modifier
                                    .size(30.dp)
                            )
                        }
                    }
                    ImageWithStateHandling(
                        imageUrl = product.image,
                        modifier = modifier
                            .padding(horizontal = 8.dp, vertical = 5.dp)
                            .constrainAs(image) {
                                top.linkTo(back.bottom, margin = 5.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )



                }
            }
            Text(
                text = product.title,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier
                    .padding(12.dp)
                    .constrainAs(title) {
                        top.linkTo(surface.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            // brand
            ItemBox(
                name = product.brand,
                icon = ImageVector.vectorResource(R.drawable.ic_company),
                modifier = modifier
                    .constrainAs(company) {
                        top.linkTo(title.bottom, 10.dp)
                        start.linkTo(parent.start)
                    }
            )

            // Price
            ItemBox(
                name = product.price.toString() + "$",
                icon = ImageVector.vectorResource(R.drawable.ic_price),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Black
                ),
                modifier = modifier
                    .constrainAs(price) {
                        top.linkTo(company.bottom, 10.dp)
                        start.linkTo(parent.start)
                    }
            )

            // Category
            ItemBox(
                name = product.category,
                icon = ImageVector.vectorResource(R.drawable.ic_category),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal
                ),
                modifier = modifier
                    .constrainAs(category) {
                        top.linkTo(price.bottom, 10.dp)
                        start.linkTo(parent.start)
                    }
            )

            // Description
            ItemBox(
                name = product.description,
                icon = Icons.Rounded.Info,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal
                ),
                modifier = modifier
                    .constrainAs(description) {
                        top.linkTo(category.bottom, 10.dp)
                        start.linkTo(parent.start)
                    }
            )
        }
    }
}

@Composable
fun ItemBox(
    name: String,
    icon: ImageVector,
    style: TextStyle = TextStyle(),
    modifier: Modifier
) {
    Surface(
        shape = Shapes().small,
        tonalElevation = 2.dp,
        modifier = modifier
            .padding(8.dp)
    ) {
        Row(
            modifier = modifier
                .padding(3.dp)
        ) {
            Box(
                modifier = modifier
                    .size(30.dp)
                    .background(MaterialTheme.colorScheme.background, shape = Shapes().small)
                    .clip(Shapes().small),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
            Spacer(modifier = modifier.width(10.dp))
            Text(
                text = name,
                style = style,
            )
        }
    }
}