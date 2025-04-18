package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.ui_utils.shimmerLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductLoadingState(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(10) {
            Surface(
                shape = Shapes().medium,
//                tonalElevation = 1.dp,
                modifier = modifier

            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier=modifier
                            .height(150.dp)
                            .clip(Shapes().medium)
                            .shimmerLoading()
                            .fillMaxWidth()
                    )
                    Spacer(modifier = modifier.height(20.dp))
                    Box(
                        modifier=modifier
                            .height(18.dp)
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth()
                            .clip(Shapes().small)
                            .shimmerLoading()
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth()
                    ){
                        Box(
                            modifier=modifier
                                .height(25.dp)
                                .clip(Shapes().small)
                                .shimmerLoading()
                                .fillMaxWidth(fraction = 0.5f)
                                .padding(horizontal = 5.dp)
                        )
                        Box(
                            modifier = modifier
                                .size(40.dp)
                                .clip(Shapes().medium)
                                .shimmerLoading()
                        )
                    }
                    Spacer(modifier = modifier.height(20.dp))
                }
            }
        }
    }
}