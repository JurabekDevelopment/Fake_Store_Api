package uz.turgunboyevjurabek.fakestoreapi.feature.domain.use_case

import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResponse
import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResult
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.MyProduct
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.repository.MyRepository

class GetProductsUseCase(private val myRepository: MyRepository) {
    suspend operator fun invoke(): MyProduct?{
        return myRepository.getProducts()
    }
}