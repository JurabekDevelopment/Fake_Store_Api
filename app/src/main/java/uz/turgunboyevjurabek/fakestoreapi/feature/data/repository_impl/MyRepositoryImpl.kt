package uz.turgunboyevjurabek.fakestoreapi.feature.data.repository_impl

import android.util.Log
import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResponse
import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResult
import uz.turgunboyevjurabek.fakestoreapi.feature.data.data_source.network.ApiService
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.MyProduct
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.repository.MyRepository


class MyRepositoryImpl(private val apiService: ApiService) : MyRepository {
    override suspend fun getProducts(): MyProduct? {
//        val response = apiService.getProducts()
//
//        return when(response){
//            is MyResponse.Error -> {
//                MyResult.error(response.message)
//            }
//            is MyResponse.Success -> {
//                MyResult.success(response.data)
//            }
//        }

        val response = apiService.getProducts()
        return when(response){
            is MyResponse.Error -> {
                Log.d("hatolik", "mana: ${response.message}")
                null
            }
            is MyResponse.Success -> {
                response.data
            }
        }

    }
}