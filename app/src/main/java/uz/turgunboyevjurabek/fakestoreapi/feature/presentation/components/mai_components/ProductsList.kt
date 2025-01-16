package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import uz.turgunboyevjurabek.fakestoreapi.R
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.Product
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.ui_utils.shimmerLoading

@Composable
fun ProductsList(
    products: SnapshotStateList<Product>,
    onItemClick: (Product) -> Unit = {},
    modifier: Modifier = Modifier
) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
        modifier = modifier
    ) {
        items(products.size) {
            ProductItem(
                onItemClick = onItemClick,
                product = products[it],
            )
        }

    }

}

@Composable
fun ProductItem(
    product: Product,
    onItemClick: (Product) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = {
            onItemClick(product)
        },
        shape = Shapes().medium,
        shadowElevation = 2.dp,
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxSize()
        ) {
            val (image, title, price, rating, like) = createRefs()
            ImageWithStateHandling(
                imageUrl = product.image,
                modifier = modifier
                    .clip(shape = Shapes().medium)
                    .fillMaxWidth()
//                    .height(200.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .background(Color.White, shape = Shapes().medium)
                    .size(40.dp)
                    .constrainAs(like) {
                        bottom.linkTo(parent.bottom, 10.dp)
                        top.linkTo(title.bottom,10.dp)
                        end.linkTo(parent.end, 10.dp)
                    }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_favorite_border),
                    tint = Color.Red,
                    contentDescription = null,
                    modifier = modifier
                        .size(30.dp)
                )
            }

//            Text(
//                "4.5 ⭐",
//                fontWeight = FontWeight.Bold,
//                fontFamily = FontFamily.SansSerif,
//                fontSize = 13.sp,
//                modifier = modifier
//                    .constrainAs(rating) {
//                        top.linkTo(image.bottom, 16.dp)
//                        end.linkTo(parent.end, 16.dp)
//                    }
//            )
            Text(
                product.title,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 13.sp,
                lineHeight = 15.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .padding(horizontal = 5.dp)
                    .alpha(0.7f)
                    .constrainAs(title) {
                        top.linkTo(image.bottom, 25.dp)
                        start.linkTo(parent.start,5.dp)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                product.price.toString()+"$",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 17.sp,
                modifier = modifier
                    .constrainAs(price) {
                        top.linkTo(title.bottom, 6.dp)
                        start.linkTo(parent.start,5.dp)
                    }
            )

        }
    }
}

@Preview
@Composable
fun ProductsListPreview() {
//    ProductItem()
}