package uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.for_navigation

import kotlinx.serialization.SerialName

data class SelectedItem(
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
