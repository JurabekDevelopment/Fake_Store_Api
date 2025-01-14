package uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("brand")
    val brand: String,
    @SerialName("category")
    val category: String,
    @SerialName("color")
    val color: String? = null,
    @SerialName("description")
    val description: String,
    @SerialName("discount")
    val discount: Int? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("model")
    val model: String,
    @SerialName("onSale")
    val onSale: Boolean? = false,
    @SerialName("popular")
    val popular: Boolean? = null,
    @SerialName("price")
    val price: Int,
    @SerialName("title")
    val title: String
)