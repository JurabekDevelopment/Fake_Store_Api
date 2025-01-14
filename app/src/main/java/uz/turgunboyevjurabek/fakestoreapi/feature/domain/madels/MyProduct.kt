package uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyProduct(
    @SerialName("message")
    val message: String,
    @SerialName("products")
    val products: ArrayList<Product>,
    @SerialName("status")
    val status: String
)