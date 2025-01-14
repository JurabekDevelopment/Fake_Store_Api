package uz.turgunboyevjurabek.fakestoreapi.feature.domain.repository

import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResponse
import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResult
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.MyProduct

interface MyRepository{
    suspend fun getProducts():MyProduct?
}