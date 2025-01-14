package uz.turgunboyevjurabek.fakestoreapi.feature.data.data_source.network

import kotlinx.coroutines.flow.Flow
import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResponse
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.MyProduct

interface ApiService {

    suspend fun getProducts(): MyResponse<MyProduct>

}