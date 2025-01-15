package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.components.mai_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uz.turgunboyevjurabek.fakestoreapi.R

@Composable
fun MySearchView(

    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null, tint = Color.Gray)
            },
            placeholder = {
                Text("Search for mobile", color = Color.Gray, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.SansSerif)
            },
            shape = Shapes().medium,
            modifier = modifier
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .background(Color(0xFFFF6D00), shape = Shapes().medium)
                .size(60.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                contentDescription = null,
                tint = Color.White,
                modifier = modifier.size(30.dp)
            )
        }

    }


}