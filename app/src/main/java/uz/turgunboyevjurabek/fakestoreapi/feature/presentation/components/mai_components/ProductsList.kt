package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import uz.turgunboyevjurabek.fakestoreapi.R

@Composable
fun ProductsList(modifier: Modifier = Modifier) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(30) {
            ProductItem()
        }

    }

}

@Composable
fun ProductItem(modifier: Modifier = Modifier) {
    Surface(
        shape = Shapes().small,
        modifier = modifier
            .height(300.dp)
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxSize()

        ) {
            val (image, title, price, rating,like) = createRefs()
            Image(
                painter = painterResource(R.drawable.images),
                contentDescription = null,
//                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(shape = Shapes().small)
                    .fillMaxWidth()
                    .height(200.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier=modifier
                    .background(Color.White, shape = Shapes().medium)
                    .size(40.dp)
                    .constrainAs(like){
                        top.linkTo(parent.top,16.dp)
                        end.linkTo(parent.end,16.dp)
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
            Text(
                "4.5 ⭐",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 13.sp,
                modifier = modifier
                    .constrainAs(rating) {
                        top.linkTo(image.bottom, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }
            )
            Text(
                "Iphone 14",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 13.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .alpha(0.7f)
                    .constrainAs(title) {
                        top.linkTo(image.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            )
            Text(
                "$1200",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 17.sp,
                modifier = modifier
                    .constrainAs(price) {
                        top.linkTo(title.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            )

        }
    }
}

@Preview
@Composable
fun ProductsListPreview() {
    ProductItem()
}