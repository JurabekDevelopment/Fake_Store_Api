package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.Product


@Serializable
data object MainRout

@Serializable
data class DetailRout(
    val brand: String,
    val category: String,
    val color: String? = null,
    val description: String,
    val discount: Int? = null,
    val id: Int,
    val image: String,
    val model: String,
    val onSale: Boolean? = false,
    val popular: Boolean? = null,
    val price: Int,
    val title: String
)
